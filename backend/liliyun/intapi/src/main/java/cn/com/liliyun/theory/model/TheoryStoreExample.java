package cn.com.liliyun.theory.model;

import java.util.ArrayList;
import java.util.List;

import cn.com.liliyun.common.model.BaseModel;

public class TheoryStoreExample extends BaseModel {
	
	private static final long serialVersionUID = 1L;

	protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TheoryStoreExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTheoryidIsNull() {
            addCriterion("theoryId is null");
            return (Criteria) this;
        }

        public Criteria andTheoryidIsNotNull() {
            addCriterion("theoryId is not null");
            return (Criteria) this;
        }

        public Criteria andTheoryidEqualTo(Integer value) {
            addCriterion("theoryId =", value, "theoryid");
            return (Criteria) this;
        }

        public Criteria andTheoryidNotEqualTo(Integer value) {
            addCriterion("theoryId <>", value, "theoryid");
            return (Criteria) this;
        }

        public Criteria andTheoryidGreaterThan(Integer value) {
            addCriterion("theoryId >", value, "theoryid");
            return (Criteria) this;
        }

        public Criteria andTheoryidGreaterThanOrEqualTo(Integer value) {
            addCriterion("theoryId >=", value, "theoryid");
            return (Criteria) this;
        }

        public Criteria andTheoryidLessThan(Integer value) {
            addCriterion("theoryId <", value, "theoryid");
            return (Criteria) this;
        }

        public Criteria andTheoryidLessThanOrEqualTo(Integer value) {
            addCriterion("theoryId <=", value, "theoryid");
            return (Criteria) this;
        }

        public Criteria andTheoryidIn(List<Integer> values) {
            addCriterion("theoryId in", values, "theoryid");
            return (Criteria) this;
        }

        public Criteria andTheoryidNotIn(List<Integer> values) {
            addCriterion("theoryId not in", values, "theoryid");
            return (Criteria) this;
        }

        public Criteria andTheoryidBetween(Integer value1, Integer value2) {
            addCriterion("theoryId between", value1, value2, "theoryid");
            return (Criteria) this;
        }

        public Criteria andTheoryidNotBetween(Integer value1, Integer value2) {
            addCriterion("theoryId not between", value1, value2, "theoryid");
            return (Criteria) this;
        }

        public Criteria andStoreidIsNull() {
            addCriterion("storeId is null");
            return (Criteria) this;
        }

        public Criteria andStoreidIsNotNull() {
            addCriterion("storeId is not null");
            return (Criteria) this;
        }

        public Criteria andStoreidEqualTo(Integer value) {
            addCriterion("storeId =", value, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidNotEqualTo(Integer value) {
            addCriterion("storeId <>", value, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidGreaterThan(Integer value) {
            addCriterion("storeId >", value, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidGreaterThanOrEqualTo(Integer value) {
            addCriterion("storeId >=", value, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidLessThan(Integer value) {
            addCriterion("storeId <", value, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidLessThanOrEqualTo(Integer value) {
            addCriterion("storeId <=", value, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidIn(List<Integer> values) {
            addCriterion("storeId in", values, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidNotIn(List<Integer> values) {
            addCriterion("storeId not in", values, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidBetween(Integer value1, Integer value2) {
            addCriterion("storeId between", value1, value2, "storeid");
            return (Criteria) this;
        }

        public Criteria andStoreidNotBetween(Integer value1, Integer value2) {
            addCriterion("storeId not between", value1, value2, "storeid");
            return (Criteria) this;
        }

        public Criteria andRecomnumIsNull() {
            addCriterion("recomNum is null");
            return (Criteria) this;
        }

        public Criteria andRecomnumIsNotNull() {
            addCriterion("recomNum is not null");
            return (Criteria) this;
        }

        public Criteria andRecomnumEqualTo(Integer value) {
            addCriterion("recomNum =", value, "recomnum");
            return (Criteria) this;
        }

        public Criteria andRecomnumNotEqualTo(Integer value) {
            addCriterion("recomNum <>", value, "recomnum");
            return (Criteria) this;
        }

        public Criteria andRecomnumGreaterThan(Integer value) {
            addCriterion("recomNum >", value, "recomnum");
            return (Criteria) this;
        }

        public Criteria andRecomnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("recomNum >=", value, "recomnum");
            return (Criteria) this;
        }

        public Criteria andRecomnumLessThan(Integer value) {
            addCriterion("recomNum <", value, "recomnum");
            return (Criteria) this;
        }

        public Criteria andRecomnumLessThanOrEqualTo(Integer value) {
            addCriterion("recomNum <=", value, "recomnum");
            return (Criteria) this;
        }

        public Criteria andRecomnumIn(List<Integer> values) {
            addCriterion("recomNum in", values, "recomnum");
            return (Criteria) this;
        }

        public Criteria andRecomnumNotIn(List<Integer> values) {
            addCriterion("recomNum not in", values, "recomnum");
            return (Criteria) this;
        }

        public Criteria andRecomnumBetween(Integer value1, Integer value2) {
            addCriterion("recomNum between", value1, value2, "recomnum");
            return (Criteria) this;
        }

        public Criteria andRecomnumNotBetween(Integer value1, Integer value2) {
            addCriterion("recomNum not between", value1, value2, "recomnum");
            return (Criteria) this;
        }

        public Criteria andArrangenumIsNull() {
            addCriterion("arrangeNum is null");
            return (Criteria) this;
        }

        public Criteria andArrangenumIsNotNull() {
            addCriterion("arrangeNum is not null");
            return (Criteria) this;
        }

        public Criteria andArrangenumEqualTo(Integer value) {
            addCriterion("arrangeNum =", value, "arrangenum");
            return (Criteria) this;
        }

        public Criteria andArrangenumNotEqualTo(Integer value) {
            addCriterion("arrangeNum <>", value, "arrangenum");
            return (Criteria) this;
        }

        public Criteria andArrangenumGreaterThan(Integer value) {
            addCriterion("arrangeNum >", value, "arrangenum");
            return (Criteria) this;
        }

        public Criteria andArrangenumGreaterThanOrEqualTo(Integer value) {
            addCriterion("arrangeNum >=", value, "arrangenum");
            return (Criteria) this;
        }

        public Criteria andArrangenumLessThan(Integer value) {
            addCriterion("arrangeNum <", value, "arrangenum");
            return (Criteria) this;
        }

        public Criteria andArrangenumLessThanOrEqualTo(Integer value) {
            addCriterion("arrangeNum <=", value, "arrangenum");
            return (Criteria) this;
        }

        public Criteria andArrangenumIn(List<Integer> values) {
            addCriterion("arrangeNum in", values, "arrangenum");
            return (Criteria) this;
        }

        public Criteria andArrangenumNotIn(List<Integer> values) {
            addCriterion("arrangeNum not in", values, "arrangenum");
            return (Criteria) this;
        }

        public Criteria andArrangenumBetween(Integer value1, Integer value2) {
            addCriterion("arrangeNum between", value1, value2, "arrangenum");
            return (Criteria) this;
        }

        public Criteria andArrangenumNotBetween(Integer value1, Integer value2) {
            addCriterion("arrangeNum not between", value1, value2, "arrangenum");
            return (Criteria) this;
        }

        public Criteria andExtraIsNull() {
            addCriterion("extra is null");
            return (Criteria) this;
        }

        public Criteria andExtraIsNotNull() {
            addCriterion("extra is not null");
            return (Criteria) this;
        }

        public Criteria andExtraEqualTo(String value) {
            addCriterion("extra =", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraNotEqualTo(String value) {
            addCriterion("extra <>", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraGreaterThan(String value) {
            addCriterion("extra >", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraGreaterThanOrEqualTo(String value) {
            addCriterion("extra >=", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraLessThan(String value) {
            addCriterion("extra <", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraLessThanOrEqualTo(String value) {
            addCriterion("extra <=", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraLike(String value) {
            addCriterion("extra like", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraNotLike(String value) {
            addCriterion("extra not like", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraIn(List<String> values) {
            addCriterion("extra in", values, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraNotIn(List<String> values) {
            addCriterion("extra not in", values, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraBetween(String value1, String value2) {
            addCriterion("extra between", value1, value2, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraNotBetween(String value1, String value2) {
            addCriterion("extra not between", value1, value2, "extra");
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