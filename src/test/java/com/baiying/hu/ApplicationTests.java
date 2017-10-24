package com.baiying.hu;

import com.baiying.hu.entity.Business;
import com.baiying.hu.service.BusinessService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
    @Autowired
    private BusinessService businessService;

    @Test
    public void contextLoads() {
        System.out.println("hello world");
    }

    @Test
    public void business() {
        List<Business> businessList = businessService.queryAllBusiness();
        System.out.println(businessList);
        List<Business> resultBusiness = getChildren(businessList, 0L);
        System.out.println(resultBusiness);
    }

    // 递归获取字节点
    private static List<Business> getChildren(List<Business> dataList, long menuId) {
        List<Business> children = new ArrayList<>();
        for (Business m : dataList) {
            if (m.getParentId() == menuId) {
                m.setChildren(getChildren(dataList, m.getId()));
                children.add(m);
            }
        }
        return children;
    }

}
