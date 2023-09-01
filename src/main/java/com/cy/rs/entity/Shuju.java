package com.cy.rs.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class Shuju implements Serializable {

    @ApiModelProperty("id主键")
    private Integer id;
    @ApiModelProperty("员工编号")
    private Integer number;
    @ApiModelProperty("员工姓名")
    private String name;
    @ApiModelProperty("员工单位")
    private String unit;
    @ApiModelProperty("员工岗位")
    private String post;
    @ApiModelProperty("员工性别")
    private String sex;
    @ApiModelProperty("政治面貌")
    private String status;
    @ApiModelProperty("最高学历")
    private String degree;
    @ApiModelProperty("年龄")
    private Integer age;
    @ApiModelProperty("工作年龄")
    private Integer seniority;
    @ApiModelProperty("是否有体育特长")
    private String one;
    @ApiModelProperty("是否有艺术特长")
    private String two;
    @ApiModelProperty("是否体育类兴趣小组成员")
    private String three;
    @ApiModelProperty("是否艺术类兴趣小组成员")
    private String four;
    @ApiModelProperty("公文写作能力")
    private String five;
    @ApiModelProperty("数据分析能力")
    private String six;
    @ApiModelProperty("新媒体营销技术水平")
    private String seven;
    @ApiModelProperty("业务规章制度掌握和执行能力")
    private String eight;
    @ApiModelProperty("创新能力（意识、行为和成效）")
    private String nine;
    @ApiModelProperty("沟通能力")
    private String ten;
    @ApiModelProperty("营销策划及执行能力")
    private String eleven;
    @ApiModelProperty("团队意识及协作能力")
    private String twelve;
    @ApiModelProperty("是否中级或以上经济师")
    private String thirteen;
    @ApiModelProperty("是否初级经济师")
    private String fourteen;
    @ApiModelProperty("是否二级烟草制品购销职业资格（最高级别）")
    private String fifteen;
    @ApiModelProperty("是否三级烟草制品购销职业资格（最高级别）")
    private String sixteen;
    @ApiModelProperty("是否四级烟草制品购销职业资格（最高级别）")
    private String seventeen;
    @ApiModelProperty("是否五级烟草制品购销职业资格（最高级别）")
    private String eighteen;
    @ApiModelProperty("是否具有中级以上计算机方面的资格证书")
    private String nineteen;
    @ApiModelProperty("是否有参加市局组织的新媒体培训经历")
    private String twenty;
    @ApiModelProperty("是否有参加市局组织的数据分析培训经历")
    private String twentyOne;
    @ApiModelProperty("是否有市局轮训经历")
    private String twentyTwo;
    @ApiModelProperty("是否有省局轮训经历")
    private String twentyThree;
    @ApiModelProperty("是否省级内训师")
    private String twentyFour;
    @ApiModelProperty("是否市级或县级内训师")
    private String twentyFive;
    @ApiModelProperty("近两年是否有参与线下授课经历")
    private String twentySix;
    @ApiModelProperty("近两年是否有参与内训师相关竞赛的经历")
    private String twentySeven;
    @ApiModelProperty("近两年是否有参加视频课程开发经历")
    private String twentyEight;
    @ApiModelProperty("是否数据分析团队成员")
    private String twentyNine;
    @ApiModelProperty("是否新媒体营销团队成员")
    private String thirty;
    @ApiModelProperty("是否省局劳模工作室成员")
    private String thirtyOne;
    @ApiModelProperty("是否省局创客工作室成员")
    private String thirtyTwo;
    @ApiModelProperty("是否有参加省局专项工作的经历")
    private String thirtyThree;
    @ApiModelProperty("当地主要使用的方言掌握情况")
    private String thirtyFour;
    @ApiModelProperty("普通话标准情况")
    private String thirtyFive;
    @ApiModelProperty("粤语掌握情况")
    private String thirtySix;
    @ApiModelProperty("参加工作前是否已定居广东")
    private String thirtySeven;
    @ApiModelProperty("参加工作前是否已定居韶关")
    private String thirtyEight;
    @ApiModelProperty("是否在工作地（区/县/市）定居")
    private String thirtyNine;
    @ApiModelProperty("是否有任职营销以外岗位的工作经历")
    private String forty;
    @ApiModelProperty("是否有任职当前岗位以外营销岗位的工作经历（客户专员、信息专员、终端专员原任客户经理的，不视作不同岗位）")
    private String fortyOne;
    @ApiModelProperty("是否已婚")
    private String fortyTwo;
    @ApiModelProperty("生育情况")
    private String fortyThree;
    @ApiModelProperty("是否有QC项目获奖情况")
    private String fortyFour;
    @ApiModelProperty("是否有论文发表或获奖情况")
    private String fortyFive;
    @ApiModelProperty("是否有文章在省局以上媒体发表情况")
    private String fortySix;
    @ApiModelProperty("是否有参与数字化转型项目情况")
    private String fortySeven;
    @ApiModelProperty("是否有作为主要成员参加营销创新项目的经历")
    private String fortyEight;
    @ApiModelProperty("是否有参与的视频项目并在省局以上媒体发表情况")
    private String fortyNine;
    @ApiModelProperty("是否有参与市局营销竞赛并获奖的情况")
    private String fifty;
    @ApiModelProperty("是否受到国家局（总公司）表彰")
    private String fiftyOne;
    @ApiModelProperty("是否受到省局（公司）表彰")
    private String fiftyTwo;
    @ApiModelProperty("是否受到市局（公司）表彰")
    private String fiftyThree;
    @ApiModelProperty("绩效")
    private Double performance;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSeniority() {
        return seniority;
    }

    public void setSeniority(Integer seniority) {
        this.seniority = seniority;
    }

    public String getOne() {
        return one;
    }

    public void setOne(String one) {
        this.one = one;
    }

    public String getTwo() {
        return two;
    }

    public void setTwo(String two) {
        this.two = two;
    }

    public String getThree() {
        return three;
    }

    public void setThree(String three) {
        this.three = three;
    }

    public String getFour() {
        return four;
    }

    public void setFour(String four) {
        this.four = four;
    }

    public String getFive() {
        return five;
    }

    public void setFive(String five) {
        this.five = five;
    }

    public String getSix() {
        return six;
    }

    public void setSix(String six) {
        this.six = six;
    }

    public String getSeven() {
        return seven;
    }

    public void setSeven(String seven) {
        this.seven = seven;
    }

    public String getEight() {
        return eight;
    }

    public void setEight(String eight) {
        this.eight = eight;
    }

    public String getNine() {
        return nine;
    }

    public void setNine(String nine) {
        this.nine = nine;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getEleven() {
        return eleven;
    }

    public void setEleven(String eleven) {
        this.eleven = eleven;
    }

    public String getTwelve() {
        return twelve;
    }

    public void setTwelve(String twelve) {
        this.twelve = twelve;
    }

    public String getThirteen() {
        return thirteen;
    }

    public void setThirteen(String thirteen) {
        this.thirteen = thirteen;
    }

    public String getFourteen() {
        return fourteen;
    }

    public void setFourteen(String fourteen) {
        this.fourteen = fourteen;
    }

    public String getFifteen() {
        return fifteen;
    }

    public void setFifteen(String fifteen) {
        this.fifteen = fifteen;
    }

    public String getSixteen() {
        return sixteen;
    }

    public void setSixteen(String sixteen) {
        this.sixteen = sixteen;
    }

    public String getSeventeen() {
        return seventeen;
    }

    public void setSeventeen(String seventeen) {
        this.seventeen = seventeen;
    }

    public String getEighteen() {
        return eighteen;
    }

    public void setEighteen(String eighteen) {
        this.eighteen = eighteen;
    }

    public String getNineteen() {
        return nineteen;
    }

    public void setNineteen(String nineteen) {
        this.nineteen = nineteen;
    }

    public String getTwenty() {
        return twenty;
    }

    public void setTwenty(String twenty) {
        this.twenty = twenty;
    }

    public String getTwentyOne() {
        return twentyOne;
    }

    public void setTwentyOne(String twentyOne) {
        this.twentyOne = twentyOne;
    }

    public String getTwentyTwo() {
        return twentyTwo;
    }

    public void setTwentyTwo(String twentyTwo) {
        this.twentyTwo = twentyTwo;
    }

    public String getTwentyThree() {
        return twentyThree;
    }

    public void setTwentyThree(String twentyThree) {
        this.twentyThree = twentyThree;
    }

    public String getTwentyFour() {
        return twentyFour;
    }

    public void setTwentyFour(String twentyFour) {
        this.twentyFour = twentyFour;
    }

    public String getTwentyFive() {
        return twentyFive;
    }

    public void setTwentyFive(String twentyFive) {
        this.twentyFive = twentyFive;
    }

    public String getTwentySix() {
        return twentySix;
    }

    public void setTwentySix(String twentySix) {
        this.twentySix = twentySix;
    }

    public String getTwentySeven() {
        return twentySeven;
    }

    public void setTwentySeven(String twentySeven) {
        this.twentySeven = twentySeven;
    }

    public String getTwentyEight() {
        return twentyEight;
    }

    public void setTwentyEight(String twentyEight) {
        this.twentyEight = twentyEight;
    }

    public String getTwentyNine() {
        return twentyNine;
    }

    public void setTwentyNine(String twentyNine) {
        this.twentyNine = twentyNine;
    }

    public String getThirty() {
        return thirty;
    }

    public void setThirty(String thirty) {
        this.thirty = thirty;
    }

    public String getThirtyOne() {
        return thirtyOne;
    }

    public void setThirtyOne(String thirtyOne) {
        this.thirtyOne = thirtyOne;
    }

    public String getThirtyTwo() {
        return thirtyTwo;
    }

    public void setThirtyTwo(String thirtyTwo) {
        this.thirtyTwo = thirtyTwo;
    }

    public String getThirtyThree() {
        return thirtyThree;
    }

    public void setThirtyThree(String thirtyThree) {
        this.thirtyThree = thirtyThree;
    }

    public String getThirtyFour() {
        return thirtyFour;
    }

    public void setThirtyFour(String thirtyFour) {
        this.thirtyFour = thirtyFour;
    }

    public String getThirtyFive() {
        return thirtyFive;
    }

    public void setThirtyFive(String thirtyFive) {
        this.thirtyFive = thirtyFive;
    }

    public String getThirtySix() {
        return thirtySix;
    }

    public void setThirtySix(String thirtySix) {
        this.thirtySix = thirtySix;
    }

    public String getThirtySeven() {
        return thirtySeven;
    }

    public void setThirtySeven(String thirtySeven) {
        this.thirtySeven = thirtySeven;
    }

    public String getThirtyEight() {
        return thirtyEight;
    }

    public void setThirtyEight(String thirtyEight) {
        this.thirtyEight = thirtyEight;
    }

    public String getThirtyNine() {
        return thirtyNine;
    }

    public void setThirtyNine(String thirtyNine) {
        this.thirtyNine = thirtyNine;
    }

    public String getForty() {
        return forty;
    }

    public void setForty(String forty) {
        this.forty = forty;
    }

    public String getFortyOne() {
        return fortyOne;
    }

    public void setFortyOne(String fortyOne) {
        this.fortyOne = fortyOne;
    }

    public String getFortyTwo() {
        return fortyTwo;
    }

    public void setFortyTwo(String fortyTwo) {
        this.fortyTwo = fortyTwo;
    }

    public String getFortyThree() {
        return fortyThree;
    }

    public void setFortyThree(String fortyThree) {
        this.fortyThree = fortyThree;
    }

    public String getFortyFour() {
        return fortyFour;
    }

    public void setFortyFour(String fortyFour) {
        this.fortyFour = fortyFour;
    }

    public String getFortyFive() {
        return fortyFive;
    }

    public void setFortyFive(String fortyFive) {
        this.fortyFive = fortyFive;
    }

    public String getFortySix() {
        return fortySix;
    }

    public void setFortySix(String fortySix) {
        this.fortySix = fortySix;
    }

    public String getFortySeven() {
        return fortySeven;
    }

    public void setFortySeven(String fortySeven) {
        this.fortySeven = fortySeven;
    }

    public String getFortyEight() {
        return fortyEight;
    }

    public void setFortyEight(String fortyEight) {
        this.fortyEight = fortyEight;
    }

    public String getFortyNine() {
        return fortyNine;
    }

    public void setFortyNine(String fortyNine) {
        this.fortyNine = fortyNine;
    }

    public String getFifty() {
        return fifty;
    }

    public void setFifty(String fifty) {
        this.fifty = fifty;
    }

    public String getFiftyOne() {
        return fiftyOne;
    }

    public void setFiftyOne(String fiftyOne) {
        this.fiftyOne = fiftyOne;
    }

    public String getFiftyTwo() {
        return fiftyTwo;
    }

    public void setFiftyTwo(String fiftyTwo) {
        this.fiftyTwo = fiftyTwo;
    }

    public String getFiftyThree() {
        return fiftyThree;
    }

    public void setFiftyThree(String fiftyThree) {
        this.fiftyThree = fiftyThree;
    }

    public Double getPerformance() {
        return performance;
    }

    public void setPerformance(Double performance) {
        this.performance = performance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shuju)) return false;

        Shuju shuju = (Shuju) o;

        if (getId() != null ? !getId().equals(shuju.getId()) : shuju.getId() != null) return false;
        if (getNumber() != null ? !getNumber().equals(shuju.getNumber()) : shuju.getNumber() != null) return false;
        if (getName() != null ? !getName().equals(shuju.getName()) : shuju.getName() != null) return false;
        if (getUnit() != null ? !getUnit().equals(shuju.getUnit()) : shuju.getUnit() != null) return false;
        if (getPost() != null ? !getPost().equals(shuju.getPost()) : shuju.getPost() != null) return false;
        if (getSex() != null ? !getSex().equals(shuju.getSex()) : shuju.getSex() != null) return false;
        if (getStatus() != null ? !getStatus().equals(shuju.getStatus()) : shuju.getStatus() != null) return false;
        if (getDegree() != null ? !getDegree().equals(shuju.getDegree()) : shuju.getDegree() != null) return false;
        if (getAge() != null ? !getAge().equals(shuju.getAge()) : shuju.getAge() != null) return false;
        if (getSeniority() != null ? !getSeniority().equals(shuju.getSeniority()) : shuju.getSeniority() != null)
            return false;
        if (getOne() != null ? !getOne().equals(shuju.getOne()) : shuju.getOne() != null) return false;
        if (getTwo() != null ? !getTwo().equals(shuju.getTwo()) : shuju.getTwo() != null) return false;
        if (getThree() != null ? !getThree().equals(shuju.getThree()) : shuju.getThree() != null) return false;
        if (getFour() != null ? !getFour().equals(shuju.getFour()) : shuju.getFour() != null) return false;
        if (getFive() != null ? !getFive().equals(shuju.getFive()) : shuju.getFive() != null) return false;
        if (getSix() != null ? !getSix().equals(shuju.getSix()) : shuju.getSix() != null) return false;
        if (getSeven() != null ? !getSeven().equals(shuju.getSeven()) : shuju.getSeven() != null) return false;
        if (getEight() != null ? !getEight().equals(shuju.getEight()) : shuju.getEight() != null) return false;
        if (getNine() != null ? !getNine().equals(shuju.getNine()) : shuju.getNine() != null) return false;
        if (getTen() != null ? !getTen().equals(shuju.getTen()) : shuju.getTen() != null) return false;
        if (getEleven() != null ? !getEleven().equals(shuju.getEleven()) : shuju.getEleven() != null) return false;
        if (getTwelve() != null ? !getTwelve().equals(shuju.getTwelve()) : shuju.getTwelve() != null) return false;
        if (getThirteen() != null ? !getThirteen().equals(shuju.getThirteen()) : shuju.getThirteen() != null)
            return false;
        if (getFourteen() != null ? !getFourteen().equals(shuju.getFourteen()) : shuju.getFourteen() != null)
            return false;
        if (getFifteen() != null ? !getFifteen().equals(shuju.getFifteen()) : shuju.getFifteen() != null) return false;
        if (getSixteen() != null ? !getSixteen().equals(shuju.getSixteen()) : shuju.getSixteen() != null) return false;
        if (getSeventeen() != null ? !getSeventeen().equals(shuju.getSeventeen()) : shuju.getSeventeen() != null)
            return false;
        if (getEighteen() != null ? !getEighteen().equals(shuju.getEighteen()) : shuju.getEighteen() != null)
            return false;
        if (getNineteen() != null ? !getNineteen().equals(shuju.getNineteen()) : shuju.getNineteen() != null)
            return false;
        if (getTwenty() != null ? !getTwenty().equals(shuju.getTwenty()) : shuju.getTwenty() != null) return false;
        if (getTwentyOne() != null ? !getTwentyOne().equals(shuju.getTwentyOne()) : shuju.getTwentyOne() != null)
            return false;
        if (getTwentyTwo() != null ? !getTwentyTwo().equals(shuju.getTwentyTwo()) : shuju.getTwentyTwo() != null)
            return false;
        if (getTwentyThree() != null ? !getTwentyThree().equals(shuju.getTwentyThree()) : shuju.getTwentyThree() != null)
            return false;
        if (getTwentyFour() != null ? !getTwentyFour().equals(shuju.getTwentyFour()) : shuju.getTwentyFour() != null)
            return false;
        if (getTwentyFive() != null ? !getTwentyFive().equals(shuju.getTwentyFive()) : shuju.getTwentyFive() != null)
            return false;
        if (getTwentySix() != null ? !getTwentySix().equals(shuju.getTwentySix()) : shuju.getTwentySix() != null)
            return false;
        if (getTwentySeven() != null ? !getTwentySeven().equals(shuju.getTwentySeven()) : shuju.getTwentySeven() != null)
            return false;
        if (getTwentyEight() != null ? !getTwentyEight().equals(shuju.getTwentyEight()) : shuju.getTwentyEight() != null)
            return false;
        if (getTwentyNine() != null ? !getTwentyNine().equals(shuju.getTwentyNine()) : shuju.getTwentyNine() != null)
            return false;
        if (getThirty() != null ? !getThirty().equals(shuju.getThirty()) : shuju.getThirty() != null) return false;
        if (getThirtyOne() != null ? !getThirtyOne().equals(shuju.getThirtyOne()) : shuju.getThirtyOne() != null)
            return false;
        if (getThirtyTwo() != null ? !getThirtyTwo().equals(shuju.getThirtyTwo()) : shuju.getThirtyTwo() != null)
            return false;
        if (getThirtyThree() != null ? !getThirtyThree().equals(shuju.getThirtyThree()) : shuju.getThirtyThree() != null)
            return false;
        if (getThirtyFour() != null ? !getThirtyFour().equals(shuju.getThirtyFour()) : shuju.getThirtyFour() != null)
            return false;
        if (getThirtyFive() != null ? !getThirtyFive().equals(shuju.getThirtyFive()) : shuju.getThirtyFive() != null)
            return false;
        if (getThirtySix() != null ? !getThirtySix().equals(shuju.getThirtySix()) : shuju.getThirtySix() != null)
            return false;
        if (getThirtySeven() != null ? !getThirtySeven().equals(shuju.getThirtySeven()) : shuju.getThirtySeven() != null)
            return false;
        if (getThirtyEight() != null ? !getThirtyEight().equals(shuju.getThirtyEight()) : shuju.getThirtyEight() != null)
            return false;
        if (getThirtyNine() != null ? !getThirtyNine().equals(shuju.getThirtyNine()) : shuju.getThirtyNine() != null)
            return false;
        if (getForty() != null ? !getForty().equals(shuju.getForty()) : shuju.getForty() != null) return false;
        if (getFortyOne() != null ? !getFortyOne().equals(shuju.getFortyOne()) : shuju.getFortyOne() != null)
            return false;
        if (getFortyTwo() != null ? !getFortyTwo().equals(shuju.getFortyTwo()) : shuju.getFortyTwo() != null)
            return false;
        if (getFortyThree() != null ? !getFortyThree().equals(shuju.getFortyThree()) : shuju.getFortyThree() != null)
            return false;
        if (getFortyFour() != null ? !getFortyFour().equals(shuju.getFortyFour()) : shuju.getFortyFour() != null)
            return false;
        if (getFortyFive() != null ? !getFortyFive().equals(shuju.getFortyFive()) : shuju.getFortyFive() != null)
            return false;
        if (getFortySix() != null ? !getFortySix().equals(shuju.getFortySix()) : shuju.getFortySix() != null)
            return false;
        if (getFortySeven() != null ? !getFortySeven().equals(shuju.getFortySeven()) : shuju.getFortySeven() != null)
            return false;
        if (getFortyEight() != null ? !getFortyEight().equals(shuju.getFortyEight()) : shuju.getFortyEight() != null)
            return false;
        if (getFortyNine() != null ? !getFortyNine().equals(shuju.getFortyNine()) : shuju.getFortyNine() != null)
            return false;
        if (getFifty() != null ? !getFifty().equals(shuju.getFifty()) : shuju.getFifty() != null) return false;
        if (getFiftyOne() != null ? !getFiftyOne().equals(shuju.getFiftyOne()) : shuju.getFiftyOne() != null)
            return false;
        if (getFiftyTwo() != null ? !getFiftyTwo().equals(shuju.getFiftyTwo()) : shuju.getFiftyTwo() != null)
            return false;
        if (getFiftyThree() != null ? !getFiftyThree().equals(shuju.getFiftyThree()) : shuju.getFiftyThree() != null)
            return false;
        return getPerformance() != null ? getPerformance().equals(shuju.getPerformance()) : shuju.getPerformance() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getNumber() != null ? getNumber().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getUnit() != null ? getUnit().hashCode() : 0);
        result = 31 * result + (getPost() != null ? getPost().hashCode() : 0);
        result = 31 * result + (getSex() != null ? getSex().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        result = 31 * result + (getDegree() != null ? getDegree().hashCode() : 0);
        result = 31 * result + (getAge() != null ? getAge().hashCode() : 0);
        result = 31 * result + (getSeniority() != null ? getSeniority().hashCode() : 0);
        result = 31 * result + (getOne() != null ? getOne().hashCode() : 0);
        result = 31 * result + (getTwo() != null ? getTwo().hashCode() : 0);
        result = 31 * result + (getThree() != null ? getThree().hashCode() : 0);
        result = 31 * result + (getFour() != null ? getFour().hashCode() : 0);
        result = 31 * result + (getFive() != null ? getFive().hashCode() : 0);
        result = 31 * result + (getSix() != null ? getSix().hashCode() : 0);
        result = 31 * result + (getSeven() != null ? getSeven().hashCode() : 0);
        result = 31 * result + (getEight() != null ? getEight().hashCode() : 0);
        result = 31 * result + (getNine() != null ? getNine().hashCode() : 0);
        result = 31 * result + (getTen() != null ? getTen().hashCode() : 0);
        result = 31 * result + (getEleven() != null ? getEleven().hashCode() : 0);
        result = 31 * result + (getTwelve() != null ? getTwelve().hashCode() : 0);
        result = 31 * result + (getThirteen() != null ? getThirteen().hashCode() : 0);
        result = 31 * result + (getFourteen() != null ? getFourteen().hashCode() : 0);
        result = 31 * result + (getFifteen() != null ? getFifteen().hashCode() : 0);
        result = 31 * result + (getSixteen() != null ? getSixteen().hashCode() : 0);
        result = 31 * result + (getSeventeen() != null ? getSeventeen().hashCode() : 0);
        result = 31 * result + (getEighteen() != null ? getEighteen().hashCode() : 0);
        result = 31 * result + (getNineteen() != null ? getNineteen().hashCode() : 0);
        result = 31 * result + (getTwenty() != null ? getTwenty().hashCode() : 0);
        result = 31 * result + (getTwentyOne() != null ? getTwentyOne().hashCode() : 0);
        result = 31 * result + (getTwentyTwo() != null ? getTwentyTwo().hashCode() : 0);
        result = 31 * result + (getTwentyThree() != null ? getTwentyThree().hashCode() : 0);
        result = 31 * result + (getTwentyFour() != null ? getTwentyFour().hashCode() : 0);
        result = 31 * result + (getTwentyFive() != null ? getTwentyFive().hashCode() : 0);
        result = 31 * result + (getTwentySix() != null ? getTwentySix().hashCode() : 0);
        result = 31 * result + (getTwentySeven() != null ? getTwentySeven().hashCode() : 0);
        result = 31 * result + (getTwentyEight() != null ? getTwentyEight().hashCode() : 0);
        result = 31 * result + (getTwentyNine() != null ? getTwentyNine().hashCode() : 0);
        result = 31 * result + (getThirty() != null ? getThirty().hashCode() : 0);
        result = 31 * result + (getThirtyOne() != null ? getThirtyOne().hashCode() : 0);
        result = 31 * result + (getThirtyTwo() != null ? getThirtyTwo().hashCode() : 0);
        result = 31 * result + (getThirtyThree() != null ? getThirtyThree().hashCode() : 0);
        result = 31 * result + (getThirtyFour() != null ? getThirtyFour().hashCode() : 0);
        result = 31 * result + (getThirtyFive() != null ? getThirtyFive().hashCode() : 0);
        result = 31 * result + (getThirtySix() != null ? getThirtySix().hashCode() : 0);
        result = 31 * result + (getThirtySeven() != null ? getThirtySeven().hashCode() : 0);
        result = 31 * result + (getThirtyEight() != null ? getThirtyEight().hashCode() : 0);
        result = 31 * result + (getThirtyNine() != null ? getThirtyNine().hashCode() : 0);
        result = 31 * result + (getForty() != null ? getForty().hashCode() : 0);
        result = 31 * result + (getFortyOne() != null ? getFortyOne().hashCode() : 0);
        result = 31 * result + (getFortyTwo() != null ? getFortyTwo().hashCode() : 0);
        result = 31 * result + (getFortyThree() != null ? getFortyThree().hashCode() : 0);
        result = 31 * result + (getFortyFour() != null ? getFortyFour().hashCode() : 0);
        result = 31 * result + (getFortyFive() != null ? getFortyFive().hashCode() : 0);
        result = 31 * result + (getFortySix() != null ? getFortySix().hashCode() : 0);
        result = 31 * result + (getFortySeven() != null ? getFortySeven().hashCode() : 0);
        result = 31 * result + (getFortyEight() != null ? getFortyEight().hashCode() : 0);
        result = 31 * result + (getFortyNine() != null ? getFortyNine().hashCode() : 0);
        result = 31 * result + (getFifty() != null ? getFifty().hashCode() : 0);
        result = 31 * result + (getFiftyOne() != null ? getFiftyOne().hashCode() : 0);
        result = 31 * result + (getFiftyTwo() != null ? getFiftyTwo().hashCode() : 0);
        result = 31 * result + (getFiftyThree() != null ? getFiftyThree().hashCode() : 0);
        result = 31 * result + (getPerformance() != null ? getPerformance().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Shuju{" +
                "id=" + id +
                ", number=" + number +
                ", name='" + name + '\'' +
                ", unit='" + unit + '\'' +
                ", post='" + post + '\'' +
                ", sex='" + sex + '\'' +
                ", status='" + status + '\'' +
                ", degree='" + degree + '\'' +
                ", age=" + age +
                ", seniority=" + seniority +
                ", one='" + one + '\'' +
                ", two='" + two + '\'' +
                ", three='" + three + '\'' +
                ", four='" + four + '\'' +
                ", five='" + five + '\'' +
                ", six='" + six + '\'' +
                ", seven='" + seven + '\'' +
                ", eight='" + eight + '\'' +
                ", nine='" + nine + '\'' +
                ", ten='" + ten + '\'' +
                ", eleven='" + eleven + '\'' +
                ", twelve='" + twelve + '\'' +
                ", thirteen='" + thirteen + '\'' +
                ", fourteen='" + fourteen + '\'' +
                ", fifteen='" + fifteen + '\'' +
                ", sixteen='" + sixteen + '\'' +
                ", seventeen='" + seventeen + '\'' +
                ", eighteen='" + eighteen + '\'' +
                ", nineteen='" + nineteen + '\'' +
                ", twenty='" + twenty + '\'' +
                ", twentyOne='" + twentyOne + '\'' +
                ", twentyTwo='" + twentyTwo + '\'' +
                ", twentyThree='" + twentyThree + '\'' +
                ", twentyFour='" + twentyFour + '\'' +
                ", twentyFive='" + twentyFive + '\'' +
                ", twentySix='" + twentySix + '\'' +
                ", twentySeven='" + twentySeven + '\'' +
                ", twentyEight='" + twentyEight + '\'' +
                ", twentyNine='" + twentyNine + '\'' +
                ", thirty='" + thirty + '\'' +
                ", thirtyOne='" + thirtyOne + '\'' +
                ", thirtyTwo='" + thirtyTwo + '\'' +
                ", thirtyThree='" + thirtyThree + '\'' +
                ", thirtyFour='" + thirtyFour + '\'' +
                ", thirtyFive='" + thirtyFive + '\'' +
                ", thirtySix='" + thirtySix + '\'' +
                ", thirtySeven='" + thirtySeven + '\'' +
                ", thirtyEight='" + thirtyEight + '\'' +
                ", thirtyNine='" + thirtyNine + '\'' +
                ", forty='" + forty + '\'' +
                ", fortyOne='" + fortyOne + '\'' +
                ", fortyTwo='" + fortyTwo + '\'' +
                ", fortyThree='" + fortyThree + '\'' +
                ", fortyFour='" + fortyFour + '\'' +
                ", fortyFive='" + fortyFive + '\'' +
                ", fortySix='" + fortySix + '\'' +
                ", fortySeven='" + fortySeven + '\'' +
                ", fortyEight='" + fortyEight + '\'' +
                ", fortyNine='" + fortyNine + '\'' +
                ", fifty='" + fifty + '\'' +
                ", fiftyOne='" + fiftyOne + '\'' +
                ", fiftyTwo='" + fiftyTwo + '\'' +
                ", fiftyThree='" + fiftyThree + '\'' +
                ", performance=" + performance +
                '}';
    }
}
