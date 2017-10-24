package com.baiying.hu.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BusinessExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BusinessExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andBusinessNameIsNull() {
            addCriterion("business_name is null");
            return (Criteria) this;
        }

        public Criteria andBusinessNameIsNotNull() {
            addCriterion("business_name is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessNameEqualTo(String value) {
            addCriterion("business_name =", value, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameNotEqualTo(String value) {
            addCriterion("business_name <>", value, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameGreaterThan(String value) {
            addCriterion("business_name >", value, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameGreaterThanOrEqualTo(String value) {
            addCriterion("business_name >=", value, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameLessThan(String value) {
            addCriterion("business_name <", value, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameLessThanOrEqualTo(String value) {
            addCriterion("business_name <=", value, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameLike(String value) {
            addCriterion("business_name like", value, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameNotLike(String value) {
            addCriterion("business_name not like", value, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameIn(List<String> values) {
            addCriterion("business_name in", values, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameNotIn(List<String> values) {
            addCriterion("business_name not in", values, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameBetween(String value1, String value2) {
            addCriterion("business_name between", value1, value2, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameNotBetween(String value1, String value2) {
            addCriterion("business_name not between", value1, value2, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessCodeIsNull() {
            addCriterion("business_code is null");
            return (Criteria) this;
        }

        public Criteria andBusinessCodeIsNotNull() {
            addCriterion("business_code is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessCodeEqualTo(Long value) {
            addCriterion("business_code =", value, "businessCode");
            return (Criteria) this;
        }

        public Criteria andBusinessCodeNotEqualTo(Long value) {
            addCriterion("business_code <>", value, "businessCode");
            return (Criteria) this;
        }

        public Criteria andBusinessCodeGreaterThan(Long value) {
            addCriterion("business_code >", value, "businessCode");
            return (Criteria) this;
        }

        public Criteria andBusinessCodeGreaterThanOrEqualTo(Long value) {
            addCriterion("business_code >=", value, "businessCode");
            return (Criteria) this;
        }

        public Criteria andBusinessCodeLessThan(Long value) {
            addCriterion("business_code <", value, "businessCode");
            return (Criteria) this;
        }

        public Criteria andBusinessCodeLessThanOrEqualTo(Long value) {
            addCriterion("business_code <=", value, "businessCode");
            return (Criteria) this;
        }

        public Criteria andBusinessCodeIn(List<Long> values) {
            addCriterion("business_code in", values, "businessCode");
            return (Criteria) this;
        }

        public Criteria andBusinessCodeNotIn(List<Long> values) {
            addCriterion("business_code not in", values, "businessCode");
            return (Criteria) this;
        }

        public Criteria andBusinessCodeBetween(Long value1, Long value2) {
            addCriterion("business_code between", value1, value2, "businessCode");
            return (Criteria) this;
        }

        public Criteria andBusinessCodeNotBetween(Long value1, Long value2) {
            addCriterion("business_code not between", value1, value2, "businessCode");
            return (Criteria) this;
        }

        public Criteria andBusinessImageIsNull() {
            addCriterion("business_image is null");
            return (Criteria) this;
        }

        public Criteria andBusinessImageIsNotNull() {
            addCriterion("business_image is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessImageEqualTo(String value) {
            addCriterion("business_image =", value, "businessImage");
            return (Criteria) this;
        }

        public Criteria andBusinessImageNotEqualTo(String value) {
            addCriterion("business_image <>", value, "businessImage");
            return (Criteria) this;
        }

        public Criteria andBusinessImageGreaterThan(String value) {
            addCriterion("business_image >", value, "businessImage");
            return (Criteria) this;
        }

        public Criteria andBusinessImageGreaterThanOrEqualTo(String value) {
            addCriterion("business_image >=", value, "businessImage");
            return (Criteria) this;
        }

        public Criteria andBusinessImageLessThan(String value) {
            addCriterion("business_image <", value, "businessImage");
            return (Criteria) this;
        }

        public Criteria andBusinessImageLessThanOrEqualTo(String value) {
            addCriterion("business_image <=", value, "businessImage");
            return (Criteria) this;
        }

        public Criteria andBusinessImageLike(String value) {
            addCriterion("business_image like", value, "businessImage");
            return (Criteria) this;
        }

        public Criteria andBusinessImageNotLike(String value) {
            addCriterion("business_image not like", value, "businessImage");
            return (Criteria) this;
        }

        public Criteria andBusinessImageIn(List<String> values) {
            addCriterion("business_image in", values, "businessImage");
            return (Criteria) this;
        }

        public Criteria andBusinessImageNotIn(List<String> values) {
            addCriterion("business_image not in", values, "businessImage");
            return (Criteria) this;
        }

        public Criteria andBusinessImageBetween(String value1, String value2) {
            addCriterion("business_image between", value1, value2, "businessImage");
            return (Criteria) this;
        }

        public Criteria andBusinessImageNotBetween(String value1, String value2) {
            addCriterion("business_image not between", value1, value2, "businessImage");
            return (Criteria) this;
        }

        public Criteria andBusinessDetailIsNull() {
            addCriterion("business_detail is null");
            return (Criteria) this;
        }

        public Criteria andBusinessDetailIsNotNull() {
            addCriterion("business_detail is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessDetailEqualTo(String value) {
            addCriterion("business_detail =", value, "businessDetail");
            return (Criteria) this;
        }

        public Criteria andBusinessDetailNotEqualTo(String value) {
            addCriterion("business_detail <>", value, "businessDetail");
            return (Criteria) this;
        }

        public Criteria andBusinessDetailGreaterThan(String value) {
            addCriterion("business_detail >", value, "businessDetail");
            return (Criteria) this;
        }

        public Criteria andBusinessDetailGreaterThanOrEqualTo(String value) {
            addCriterion("business_detail >=", value, "businessDetail");
            return (Criteria) this;
        }

        public Criteria andBusinessDetailLessThan(String value) {
            addCriterion("business_detail <", value, "businessDetail");
            return (Criteria) this;
        }

        public Criteria andBusinessDetailLessThanOrEqualTo(String value) {
            addCriterion("business_detail <=", value, "businessDetail");
            return (Criteria) this;
        }

        public Criteria andBusinessDetailLike(String value) {
            addCriterion("business_detail like", value, "businessDetail");
            return (Criteria) this;
        }

        public Criteria andBusinessDetailNotLike(String value) {
            addCriterion("business_detail not like", value, "businessDetail");
            return (Criteria) this;
        }

        public Criteria andBusinessDetailIn(List<String> values) {
            addCriterion("business_detail in", values, "businessDetail");
            return (Criteria) this;
        }

        public Criteria andBusinessDetailNotIn(List<String> values) {
            addCriterion("business_detail not in", values, "businessDetail");
            return (Criteria) this;
        }

        public Criteria andBusinessDetailBetween(String value1, String value2) {
            addCriterion("business_detail between", value1, value2, "businessDetail");
            return (Criteria) this;
        }

        public Criteria andBusinessDetailNotBetween(String value1, String value2) {
            addCriterion("business_detail not between", value1, value2, "businessDetail");
            return (Criteria) this;
        }

        public Criteria andBusinessTitleIsNull() {
            addCriterion("business_title is null");
            return (Criteria) this;
        }

        public Criteria andBusinessTitleIsNotNull() {
            addCriterion("business_title is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessTitleEqualTo(String value) {
            addCriterion("business_title =", value, "businessTitle");
            return (Criteria) this;
        }

        public Criteria andBusinessTitleNotEqualTo(String value) {
            addCriterion("business_title <>", value, "businessTitle");
            return (Criteria) this;
        }

        public Criteria andBusinessTitleGreaterThan(String value) {
            addCriterion("business_title >", value, "businessTitle");
            return (Criteria) this;
        }

        public Criteria andBusinessTitleGreaterThanOrEqualTo(String value) {
            addCriterion("business_title >=", value, "businessTitle");
            return (Criteria) this;
        }

        public Criteria andBusinessTitleLessThan(String value) {
            addCriterion("business_title <", value, "businessTitle");
            return (Criteria) this;
        }

        public Criteria andBusinessTitleLessThanOrEqualTo(String value) {
            addCriterion("business_title <=", value, "businessTitle");
            return (Criteria) this;
        }

        public Criteria andBusinessTitleLike(String value) {
            addCriterion("business_title like", value, "businessTitle");
            return (Criteria) this;
        }

        public Criteria andBusinessTitleNotLike(String value) {
            addCriterion("business_title not like", value, "businessTitle");
            return (Criteria) this;
        }

        public Criteria andBusinessTitleIn(List<String> values) {
            addCriterion("business_title in", values, "businessTitle");
            return (Criteria) this;
        }

        public Criteria andBusinessTitleNotIn(List<String> values) {
            addCriterion("business_title not in", values, "businessTitle");
            return (Criteria) this;
        }

        public Criteria andBusinessTitleBetween(String value1, String value2) {
            addCriterion("business_title between", value1, value2, "businessTitle");
            return (Criteria) this;
        }

        public Criteria andBusinessTitleNotBetween(String value1, String value2) {
            addCriterion("business_title not between", value1, value2, "businessTitle");
            return (Criteria) this;
        }

        public Criteria andBusinessHotIsNull() {
            addCriterion("business_hot is null");
            return (Criteria) this;
        }

        public Criteria andBusinessHotIsNotNull() {
            addCriterion("business_hot is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessHotEqualTo(Integer value) {
            addCriterion("business_hot =", value, "businessHot");
            return (Criteria) this;
        }

        public Criteria andBusinessHotNotEqualTo(Integer value) {
            addCriterion("business_hot <>", value, "businessHot");
            return (Criteria) this;
        }

        public Criteria andBusinessHotGreaterThan(Integer value) {
            addCriterion("business_hot >", value, "businessHot");
            return (Criteria) this;
        }

        public Criteria andBusinessHotGreaterThanOrEqualTo(Integer value) {
            addCriterion("business_hot >=", value, "businessHot");
            return (Criteria) this;
        }

        public Criteria andBusinessHotLessThan(Integer value) {
            addCriterion("business_hot <", value, "businessHot");
            return (Criteria) this;
        }

        public Criteria andBusinessHotLessThanOrEqualTo(Integer value) {
            addCriterion("business_hot <=", value, "businessHot");
            return (Criteria) this;
        }

        public Criteria andBusinessHotIn(List<Integer> values) {
            addCriterion("business_hot in", values, "businessHot");
            return (Criteria) this;
        }

        public Criteria andBusinessHotNotIn(List<Integer> values) {
            addCriterion("business_hot not in", values, "businessHot");
            return (Criteria) this;
        }

        public Criteria andBusinessHotBetween(Integer value1, Integer value2) {
            addCriterion("business_hot between", value1, value2, "businessHot");
            return (Criteria) this;
        }

        public Criteria andBusinessHotNotBetween(Integer value1, Integer value2) {
            addCriterion("business_hot not between", value1, value2, "businessHot");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNull() {
            addCriterion("parent_id is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(Long value) {
            addCriterion("parent_id =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(Long value) {
            addCriterion("parent_id <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(Long value) {
            addCriterion("parent_id >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(Long value) {
            addCriterion("parent_id >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(Long value) {
            addCriterion("parent_id <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(Long value) {
            addCriterion("parent_id <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<Long> values) {
            addCriterion("parent_id in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<Long> values) {
            addCriterion("parent_id not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(Long value1, Long value2) {
            addCriterion("parent_id between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(Long value1, Long value2) {
            addCriterion("parent_id not between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNull() {
            addCriterion("created_at is null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNotNull() {
            addCriterion("created_at is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtEqualTo(Date value) {
            addCriterion("created_at =", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualTo(Date value) {
            addCriterion("created_at <>", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThan(Date value) {
            addCriterion("created_at >", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("created_at >=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThan(Date value) {
            addCriterion("created_at <", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualTo(Date value) {
            addCriterion("created_at <=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIn(List<Date> values) {
            addCriterion("created_at in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotIn(List<Date> values) {
            addCriterion("created_at not in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtBetween(Date value1, Date value2) {
            addCriterion("created_at between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotBetween(Date value1, Date value2) {
            addCriterion("created_at not between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIsNull() {
            addCriterion("updated_at is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIsNotNull() {
            addCriterion("updated_at is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtEqualTo(Date value) {
            addCriterion("updated_at =", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotEqualTo(Date value) {
            addCriterion("updated_at <>", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThan(Date value) {
            addCriterion("updated_at >", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("updated_at >=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThan(Date value) {
            addCriterion("updated_at <", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanOrEqualTo(Date value) {
            addCriterion("updated_at <=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIn(List<Date> values) {
            addCriterion("updated_at in", values, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotIn(List<Date> values) {
            addCriterion("updated_at not in", values, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtBetween(Date value1, Date value2) {
            addCriterion("updated_at between", value1, value2, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotBetween(Date value1, Date value2) {
            addCriterion("updated_at not between", value1, value2, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}