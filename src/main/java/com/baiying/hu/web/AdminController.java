package com.baiying.hu.web;

import com.alibaba.fastjson.JSON;
import com.baiying.hu.common.exception.DaVinceValidationException;
import com.baiying.hu.common.validate.ValidateService;
import com.baiying.hu.config.Constant;
import com.baiying.hu.entity.*;
import com.baiying.hu.entity.dto.ConsultantDto;
import com.baiying.hu.entity.dto.NewsDto;
import com.baiying.hu.entity.dto.ProjectEditDto;
import com.baiying.hu.entity.dto.UserLoginDto;
import com.baiying.hu.entity.vo.ConsultantResult;
import com.baiying.hu.entity.vo.ProblemResult;
import com.baiying.hu.service.*;
import com.baiying.hu.util.CookieUtils;
import com.baiying.hu.util.FileUploadAddressUtils;
import com.baiying.hu.util.TokenProcessor;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.InetAddress;
import java.net.URLConnection;
import java.util.*;

/**
 * Created by jmx
 * 2017/10/5.
 */
@Slf4j
@RestController
@RequestMapping("/admin")
@Api(value = "管理员相关api", description = "管理员相关api")
public class AdminController {
    @Autowired
    private CityService cityService;
    @Autowired
    private UserService userService;

    @Autowired
    private ConsultantService consultantService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProblemService problemService;

    @Autowired
    BusinessService businessService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private AdminService adminService;

    @Value("${upload.base.path}")
    private String path;
    @Value("${upload.shrinkage.path}")
    private String pathSl;


    @ApiOperation(value = "管理员登录", notes = "管理员登录", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping("/login")
    public ResultModel login(@RequestBody UserLoginDto record, HttpServletResponse response) throws Exception {
        //登录数据校验
//        ValidateService.valid(record);
        ConsultantExample example = new ConsultantExample();
        ConsultantResult consultantResult;
        //账号密码数据校验
        example.createCriteria().andPhoneEqualTo(String.valueOf(record.getPhone())).andPasswordEqualTo(record.getPassword());
        try {
            consultantResult = consultantService.getConstantBySelect(example).get(0);
        } catch (Exception e) {
            return new ResultModel(400, "用户名或密码错误");
        }

        if (Objects.isNull(consultantResult)) {
            return new ResultModel(400, "用户名或密码错误");
        } else {
            Consultant consultant = new Consultant();
            //生成token
            String token = TokenProcessor.getInstance().generateToken(String.valueOf(record.getPhone()), true);
            consultant.setToken(token);
            consultant.setId(consultantResult.getId());
            consultant.setActualName(consultantResult.getActualName());
            consultantService.updateConsultantById(consultant);
            CookieUtils.setAdminTokenToCookie(response, token);
            return new ResultModel(200, consultant);
        }
    }

    /**
     * 删除城市
     */
    @ApiOperation(value = "删除城市", notes = "删除城市", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/deleteCityById")
    public ResultModel deleteCityById(@ApiParam @RequestParam long id) {
        cityService.fail(id);
        return new ResultModel(200, Constant.OPERATION_OK);
    }

    /**
     * 新增城市
     */
    @PostMapping("/addOrUpdateCity")
    public ResultModel addCity(@RequestBody RecruitCity record) {
        if (!Objects.isNull(record.getId())) {
            cityService.update(record);
        } else {
            cityService.insert(record);
        }
        return new ResultModel(200, JSON.toJSON(Constant.OPERATION_OK));
    }

    /**
     * 城市列表
     */
    @GetMapping("/queryCityList")
    public ResultModel queryCityList(@RequestParam int pageSize, int pageNumber) {
        PageInfo pageInfo = PageHelper.startPage(pageNumber, pageSize).doSelectPageInfo(() -> cityService.getAllCity());
        Map<String, Object> mapResult = new LinkedHashMap<>();
        mapResult.put("rows", pageInfo.getList());
        mapResult.put("total", pageInfo.getTotal());
        return new ResultModel(200, JSON.toJSON(mapResult));
    }

    /**
     * 用户列表查询
     */
    @ApiOperation(value = "用户列表查询", notes = "用户列表查询", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/queryUserList")
    public ResultModel queryUserList(@ApiParam @RequestParam int pageSize, int pageNumber) {
        PageInfo pageInfo = PageHelper.startPage(pageNumber, pageSize).doSelectPageInfo(() -> userService.getUserList());
        Map<String, Object> mapResult = new LinkedHashMap<>();
        mapResult.put("rows", pageInfo.getList());
        mapResult.put("total", pageInfo.getTotal());
        return new ResultModel(200, mapResult);
    }

    /**
     * 新增顾问
     */
    @ApiOperation(value = "新增顾问", notes = "新增顾问", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping("/insertConsultant")
    public ResultModel insertConsultant(@RequestBody Consultant consultant) {
        //校验顾问是否已经存在
        ConsultantExample example = new ConsultantExample();
        example.createCriteria().andPhoneEqualTo(consultant.getPhone());
        List<ConsultantResult> resultList = consultantService.getConstantBySelect(example);
        if (resultList != null && !resultList.isEmpty()) {
            throw new DaVinceValidationException("顾问已经注册");
        } else {
            consultant.setRole(Constant.CONS);
            consultant.setPassword("123456");
            consultantService.insert(consultant);
        }
        return new ResultModel(200, Constant.OPERATION_OK);
    }

    /**
     * 获取登录顾问信息
     */
    @ApiOperation(value = "获取顾问信息", notes = "获取顾问信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/getConsultantInfo")
    public ResultModel getConsultantInfo(HttpServletRequest request) {
        ConsultantResult consultantResult = consultantService.getConsultantInfo(request);
        return new ResultModel(200, consultantResult);
    }

    /**
     * 管理员查看顾问列表
     */
    @GetMapping("/getConsultantList")
    @ApiOperation(value = "管理员查看顾问列表", notes = "管理员查看顾问列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, response = ConsultantResult.class
    )
    public ResultModel getConsultantList(@ApiParam @RequestParam int pageSize, int pageNumber) {
        ConsultantExample example = new ConsultantExample();
        ConsultantExample.Criteria criteria = example.createCriteria();
        criteria.andRoleEqualTo(Constant.CONS);
        PageInfo pageInfo = PageHelper.startPage(pageNumber, pageSize).doSelectPageInfo(() -> consultantService.getConstantBySelect(example));
        Map<String, Object> mapResult = new LinkedHashMap<>();
        mapResult.put("rows", pageInfo.getList());
        mapResult.put("total", pageInfo.getTotal());
        return new ResultModel(200, mapResult);
    }

    /**
     * 顾问详情
     */
    @ApiOperation(value = "顾问详情", notes = "顾问详情", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, response = Consultant.class
    )
    @GetMapping("/getConsultantDetail")
    public ResultModel getConsultantDetail(@ApiParam @RequestParam long id) {
        return new ResultModel(200, consultantService.getConsultantDetail(id));
    }

    /**
     * 查看顾问个人信息
     */
    @ApiOperation(value = "查看顾问个人信息", notes = "查看顾问个人信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, response = Consultant.class
    )
    @GetMapping("/getConsInfoByToken")
    public ResultModel getConsInfoByToken(@ApiParam HttpServletRequest request) {
        ConsultantResult consultantResult = consultantService.getConsultantInfo(request);
        return new ResultModel(200, consultantResult);
    }

    /**
     * 完善顾问信息
     */
    @PostMapping("/perfectPersonalInfo")
    @ApiOperation(value = "完善顾问信息", notes = "完善顾问信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultModel perfectPersonalInfo(@RequestBody ConsultantDto dto, HttpServletRequest request) throws Exception {
        ValidateService.valid(dto);
        ConsultantResult consultantResult = consultantService.getConsultantInfo(request);
        Consultant consultant = new Consultant();
        BeanUtils.copyProperties(dto, consultant);
        consultant.setId(consultantResult.getId());
        consultant.setStatus((byte) 1);
        consultantService.updateConsultantById(consultant);
        return new ResultModel(200, Constant.OPERATION_OK);
    }

    /**
     * 获取发布信息列表
     *
     * @return
     */
    @GetMapping("/getProjectList")
    @ApiOperation(value = "发布信息列表", notes = "发布信息列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, response = Project.class
    )
    public ResultModel getProjectList(@RequestParam int pageSize, int pageNumber) {
        PageInfo pageInfo = PageHelper.startPage(pageNumber, pageSize).doSelectPageInfo(() -> projectService.getProjectListBySelect(new ProjectExample()));
        Map<String, Object> mapResult = new LinkedHashMap<>();
        mapResult.put("rows", pageInfo.getList());
        mapResult.put("total", pageInfo.getTotal());
        return new ResultModel(200, mapResult);
    }

    /**
     * 项目编辑
     * todo
     */
    @PostMapping("/editProjectInfo")
    @ApiOperation(value = "保存项目信息", notes = "保存项目信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultModel editProjectInfo(@RequestBody ProjectEditDto record) {

        projectService.editProject(record);
        return new ResultModel(200, Constant.OPERATION_OK);
    }

    /**
     * 获取服务商列表
     */
    @GetMapping("/getServiceList")
    @ApiOperation(value = "发布信息列表", notes = "发布信息列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultModel getServiceList() {
        return new ResultModel(200, userService.getServiceList());
    }

    /**
     * 获取问题列表
     */
    @GetMapping("/getProblemList")
    @ApiOperation(value = "提问列表", notes = "提问列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, response = Problem.class
    )
    public ResultModel getProblemList(@RequestParam int pageSize, int pageNumber) {
        PageInfo pageInfo = PageHelper.startPage(pageNumber, pageSize).doSelectPageInfo(() -> problemService.queryAllProblem(new ProblemExample()));
        Map<String, Object> mapResult = new LinkedHashMap<>();
        mapResult.put("rows", pageInfo.getList());
        mapResult.put("total", pageInfo.getTotal());
        return new ResultModel(200, mapResult);
    }

    /**
     * 问题详情
     */
    @ApiOperation(value = "提问详情", notes = "提问详情", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, response = ProblemResult.class
    )
    @GetMapping("/queryDetailById")
    public ResultModel queryProblemDetail(@ApiParam @RequestParam long id) {
        return new ResultModel(200, problemService.queryDetailById(id));
    }
    /**
     * 问题添加信息
     * todo
     */
    /**
     * 服务列表
     * todo
     */
    @ApiOperation(value = "服务列表", notes = "服务列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, response = Business.class
    )
    @GetMapping("/queryBusinessList")
    public ResultModel queryBusinessList(@RequestParam int pageSize, int pageNumber) {
        PageInfo pageInfo = PageHelper.startPage(pageNumber, pageSize).doSelectPageInfo(() -> businessService.queryAllBusiness());
        Map<String, Object> mapResult = new LinkedHashMap<>();
        mapResult.put("rows", pageInfo.getList());
        mapResult.put("total", pageInfo.getTotal());
        return new ResultModel(200, mapResult);
    }

    /**
     * 服务insertOrUpdate
     * todo
     */
    @ApiOperation(value = "服务新增or修改", notes = "服务新增or修改", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping("/addOrUpdateBusiness")
    public ResultModel addOrUpdateBusiness(@RequestBody Business business) {
        businessService.insertBusiness(business);
        return new ResultModel(200, Constant.OPERATION_OK);
    }

    /**
     * 服务删除
     * todo
     */
    @ApiOperation(value = "服务删除", notes = "服务删除", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/delBusinessById")
    public ResultModel delBusinessById(@RequestParam long id) {
        businessService.deleteBusiness(id);
        return new ResultModel(200, Constant.OPERATION_OK);
    }

    /**
     * 文件上传
     */
    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    public ResultModel testUploadFile(HttpServletRequest req, @RequestParam("file") MultipartFile file) throws IOException {
        if (file.getSize() > 50817378) {
            throw new DaVinceValidationException("文件过大,请修改");
        }
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String fileName = timeInMillis + suffix;
        File filePath = new File(path + fileName);
        log.info(path);
        if (!file.isEmpty()) {
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(filePath));
            out.write(file.getBytes());
            out.flush();
            out.close();
        }
        if (suffix.equals(".png") || suffix.equals(".jpg") || suffix.equals(".JPEG")) {
            /**
             * 缩略图begin
             */
            try {
                Thumbnails.of(file.getInputStream()).size(300, 400).outputQuality(1f).toFile(pathSl + fileName);

            } catch (Exception e1) {
                return new ResultModel(400, "操作失败", e1.getMessage());
            }
            /**
             * 缩略图end
             */
        }
        return new ResultModel(200, JSON.toJSON(fileName));

    }

    @ApiOperation(value = "多文件上传", notes = "多文件上传", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/multiUpload")
    public MultiUploadResult multiUpload(HttpServletRequest request)
            throws IllegalStateException, IOException {
        MultiUploadResult m = new MultiUploadResult();
        m.setErrno(0);
        MultiValueMap<String, MultipartFile> files = ((MultipartHttpServletRequest) request)
                .getMultiFileMap();
        if (null != files && files.size() > 0) {
            List<String> list = Lists.newArrayList();
            //遍历并保存文件
            for (List<MultipartFile> v : files.values()) {
                for (MultipartFile file : v) {
                    long timeInMillis = Calendar.getInstance().getTimeInMillis();
                    String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                    String fileName = timeInMillis + suffix;
                    File filePath = new File(path + fileName);
                    log.info(path);
                    if (!file.isEmpty()) {
                        file.transferTo(filePath);
                        list.add(FileUploadAddressUtils.getImageAddress(Constant.ip, fileName));
                        log.info(list.toString());
                    }
                }

            }
            m.setData(list);
        }
        return m;
    }

    @PostMapping("/insertNews")
    @ApiOperation(value = "问题消息添加", notes = "问题消息添加", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultModel insertNews(@RequestBody NewsDto dto, HttpServletRequest request) {
        ConsultantResult consultantResult = consultantService.getConsultantInfo(request);
        dto.setReplyId(consultantResult.getId());
        newsService.insertNewsByConsultant(dto);
        return new ResultModel(200, Constant.OPERATION_OK);
    }

    /**
     * 顾问删除
     */
    @GetMapping("/deleteConsultantById")
    @ApiOperation(value = "顾问删除", notes = "顾问删除", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultModel deleteConsultantById(@ApiParam @RequestParam Long id) {
        consultantService.delConsultantById(id);
        return new ResultModel(200, Constant.OPERATION_OK);
    }

    @GetMapping("/getProjectDetail")
    @ApiOperation(value = "项目详情", notes = "项目详情", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, response = Project.class
    )
    public ResultModel getProjectDetail(@ApiParam @RequestParam long id) {
        ProjectExample example = new ProjectExample();
        example.createCriteria().andIdEqualTo(id);
        List<Project> resultList = projectService.getProjectListBySelect(example);
        return new ResultModel(200, resultList.get(0));
    }

    /**
     * 用户认证服务商
     */
    @GetMapping("/authorServiceProvider")
    @ApiOperation(value = "认证服务商", notes = "认证服务商", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultModel authorServiceProvider(@ApiParam @RequestParam long id, int status) {
        adminService.authorServiceProvider(id, status, 0);
        return new ResultModel(200, Constant.OPERATION_OK);
    }

    /**
     * 用户企业认证
     */
    @GetMapping("/authorCompany")
    @ApiOperation(value = "用户企业认证", notes = "用户企业认证", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultModel authorCompany(@ApiParam @RequestParam long id, int status) {
        adminService.authorServiceProvider(id, status, 1);
        return new ResultModel(200, Constant.OPERATION_OK);
    }

    @ApiOperation(value = "服务搜索列表", notes = "服务搜索列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, response = Business.class
    )
    @GetMapping("/searchBusinessByName")
    public ResultModel searchBusinessByName(@ApiParam @RequestParam(required = false) String name) {
        return new ResultModel(200, businessService.queryBusinessByName(name));
    }

}
