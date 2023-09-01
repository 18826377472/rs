package com.cy.rs.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
@ApiModel("员工信息实体类")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Employee implements Serializable {
    @ExcelProperty("序号")
    @ApiModelProperty("id主键")
    private Integer id;

    @ExcelProperty("员工编号")
    @ApiModelProperty("员工编号")
    private Integer number;

    @ExcelProperty("姓名")
    @ApiModelProperty("员工姓名")
    private String name;

    @ExcelProperty("单位")
    @ApiModelProperty("员工单位")
    private String unit;

    @ExcelProperty("岗位")
    @ApiModelProperty("员工岗位")
    private String post;

    @ExcelProperty("性别")
    @ApiModelProperty("员工性别")
    private String sex;

    @ExcelProperty("政治面貌")
    @ApiModelProperty("政治面貌")
    private String status;

    @ExcelProperty("最高学历")
    @ApiModelProperty("最高学历")
    private String degree;

    @ExcelProperty("年龄（周岁）")
    @ApiModelProperty("年龄")
    private Integer age;

    @ExcelProperty("烟草工作年限")
    @ApiModelProperty("工作年龄")
    private Integer seniority;

    @ExcelProperty("是否有体育特长")
    @ApiModelProperty("是否有体育特长")
    private String one;

    @ExcelProperty("是否有艺术特长")
    @ApiModelProperty("是否有艺术特长")
    private String two;

    @ExcelProperty("是否体育类兴趣小组成员")
    @ApiModelProperty("是否体育类兴趣小组成员")
    private String three;

    @ExcelProperty("是否艺术类兴趣小组成员")
    @ApiModelProperty("是否艺术类兴趣小组成员")
    private String four;

    @ExcelProperty("公文写作能力")
    @ApiModelProperty("公文写作能力")
    private String five;

    @ExcelProperty("数据分析能力")
    @ApiModelProperty("数据分析能力")
    private String six;

    @ExcelProperty("新媒体营销技术水平")
    @ApiModelProperty("新媒体营销技术水平")
    private String seven;

    @ExcelProperty("业务规章制度掌握和执行能力")
    @ApiModelProperty("业务规章制度掌握和执行能力")
    private String eight;

    @ExcelProperty("创新能力（意识、行为和成效）")
    @ApiModelProperty("创新能力（意识、行为和成效）")
    private String nine;

    @ExcelProperty("沟通能力")
    @ApiModelProperty("沟通能力")
    private String ten;

    @ExcelProperty("营销策划及执行能力")
    @ApiModelProperty("营销策划及执行能力")
    private String eleven;

    @ExcelProperty("团队意识及协作能力")
    @ApiModelProperty("团队意识及协作能力")
    private String twelve;

    @ExcelProperty("是否中级或以上经济师")
    @ApiModelProperty("是否中级或以上经济师")
    private String thirteen;

    @ExcelProperty("是否初级经济师")
    @ApiModelProperty("是否初级经济师")
    private String fourteen;

    @ExcelProperty("是否二级烟草制品购销职业资格（最高级别）")
    @ApiModelProperty("是否二级烟草制品购销职业资格（最高级别）")
    private String fifteen;

    @ExcelProperty("是否三级烟草制品购销职业资格（最高级别）")
    @ApiModelProperty("是否三级烟草制品购销职业资格（最高级别）")
    private String sixteen;


    @ExcelProperty("是否四级烟草制品购销职业资格（最高级别）")
    @ApiModelProperty("是否四级烟草制品购销职业资格（最高级别）")
    private String seventeen;

    @ExcelProperty("是否五级烟草制品购销职业资格（最高级别）")
    @ApiModelProperty("是否五级烟草制品购销职业资格（最高级别）")
    private String eighteen;

    @ExcelProperty("是否具有中级以上计算机方面的资格证书")
    @ApiModelProperty("是否具有中级以上计算机方面的资格证书")
    private String nineteen;

    @ExcelProperty("是否有参加市局组织的新媒体培训经历")
    @ApiModelProperty("是否有参加市局组织的新媒体培训经历")
    private String twenty;

    @ExcelProperty("是否有参加市局组织的数据分析培训经历")
    @ApiModelProperty("是否有参加市局组织的数据分析培训经历")
    private String twentyOne;

    @ExcelProperty("是否有市局轮训经历")
    @ApiModelProperty("是否有市局轮训经历")
    private String twentyTwo;

    @ExcelProperty("是否有省局轮训经历")
    @ApiModelProperty("是否有省局轮训经历")
    private String twentyThree;

    @ExcelProperty("是否省级内训师")
    @ApiModelProperty("是否省级内训师")
    private String twentyFour;

    @ExcelProperty("是否市级或县级内训师")
    @ApiModelProperty("是否市级或县级内训师")
    private String twentyFive;

    @ExcelProperty("近两年是否有参与线下授课经历")
    @ApiModelProperty("近两年是否有参与线下授课经历")
    private String twentySix;

    @ExcelProperty("近两年是否有参与内训师相关竞赛的经历")
    @ApiModelProperty("近两年是否有参与内训师相关竞赛的经历")
    private String twentySeven;

    @ExcelProperty("近两年是否有参加视频课程开发经历")
    @ApiModelProperty("近两年是否有参加视频课程开发经历")
    private String twentyEight;

    @ExcelProperty("是否数据分析团队成员")
    @ApiModelProperty("是否数据分析团队成员")
    private String twentyNine;

    @ExcelProperty("是否新媒体营销团队成员")
    @ApiModelProperty("是否新媒体营销团队成员")
    private String thirty;

    @ExcelProperty("是否省局劳模工作室成员")
    @ApiModelProperty("是否省局劳模工作室成员")
    private String thirtyOne;

    @ExcelProperty("是否省局创客工作室成员")
    @ApiModelProperty("是否省局创客工作室成员")
    private String thirtyTwo;

    @ExcelProperty("是否有参加省局专项工作的经历")
    @ApiModelProperty("是否有参加省局专项工作的经历")
    private String thirtyThree;

    @ExcelProperty("当地主要使用的方言掌握情况")
    @ApiModelProperty("当地主要使用的方言掌握情况")
    private String thirtyFour;

    @ExcelProperty("普通话标准情况")
    @ApiModelProperty("普通话标准情况")
    private String thirtyFive;

    @ExcelProperty("粤语掌握情况")
    @ApiModelProperty("粤语掌握情况")
    private String thirtySix;

    @ExcelProperty("参加工作前是否已定居广东")
    @ApiModelProperty("参加工作前是否已定居广东")
    private String thirtySeven;

    @ExcelProperty("参加工作前是否已定居韶关")
    @ApiModelProperty("参加工作前是否已定居韶关")
    private String thirtyEight;

    @ExcelProperty("是否在工作地（区/县/市）定居")
    @ApiModelProperty("是否在工作地（区/县/市）定居")
    private String thirtyNine;

    @ExcelProperty("是否有任职营销以外岗位的工作经历")
    @ApiModelProperty("是否有任职营销以外岗位的工作经历")
    private String forty;

    @ExcelProperty("是否有任职当前岗位以外营销岗位的工作经历（客户专员、信息专员、终端专员原任客户经理的，不视作不同岗位）")
    @ApiModelProperty("是否有任职当前岗位以外营销岗位的工作经历（客户专员、信息专员、终端专员原任客户经理的，不视作不同岗位）")
    private String fortyOne;

    @ExcelProperty("是否已婚")
    @ApiModelProperty("是否已婚")
    private String fortyTwo;

    @ExcelProperty("生育情况")
    @ApiModelProperty("生育情况")
    private String fortyThree;

    @ExcelProperty("是否有QC项目获奖情况")
    @ApiModelProperty("是否有QC项目获奖情况")
    private String fortyFour;

    @ExcelProperty("是否有论文发表或获奖情况")
    @ApiModelProperty("是否有论文发表或获奖情况")
    private String fortyFive;

    @ExcelProperty("是否有文章在省局以上媒体发表情况")
    @ApiModelProperty("是否有文章在省局以上媒体发表情况")
    private String fortySix;

    @ExcelProperty("是否有参与数字化转型项目情况")
    @ApiModelProperty("是否有参与数字化转型项目情况")
    private String fortySeven;

    @ExcelProperty("是否有作为主要成员参加营销创新项目的经历")
    @ApiModelProperty("是否有作为主要成员参加营销创新项目的经历")
    private String fortyEight;

    @ExcelProperty("是否有参与的视频项目并在省局以上媒体发表情况")
    @ApiModelProperty("是否有参与的视频项目并在省局以上媒体发表情况")
    private String fortyNine;

    @ExcelProperty("是否有参与市局营销竞赛并获奖的情况")
    @ApiModelProperty("是否有参与市局营销竞赛并获奖的情况")
    private String fifty;

    @ExcelProperty("是否受到国家局（总公司）表彰")
    @ApiModelProperty("是否受到国家局（总公司）表彰")
    private String fiftyOne;

    @ExcelProperty("是否受到省局（公司）表彰")
    @ApiModelProperty("是否受到省局（公司）表彰")
    private String fiftyTwo;

    @ExcelProperty("是否受到市局（公司）表彰")
    @ApiModelProperty("是否受到市局（公司）表彰")
    private String fiftyThree;

    @ExcelProperty("日期")
    @ApiModelProperty("日期")
    private String createdTime;

    public Employee() {
    }

    public Employee(Integer number, String name, String unit, String post, String sex, String status, String degree, Integer age, Integer seniority, String one, String two, String three, String four, String five, String six, String seven, String eight, String nine, String ten, String eleven, String twelve, String thirteen, String fourteen, String fifteen, String sixteen, String seventeen, String eighteen, String nineteen, String twenty, String twentyOne, String twentyTwo, String twentyThree, String twentyFour, String twentyFive, String twentySix, String twentySeven, String twentyEight, String twentyNine, String thirty, String thirtyOne, String thirtyTwo, String thirtyThree, String thirtyFour, String thirtyFive, String thirtySix, String thirtySeven, String thirtyEight, String thirtyNine, String forty, String fortyOne, String fortyTwo, String fortyThree, String fortyFour, String fortyFive, String fortySix, String fortySeven, String fortyEight, String fortyNine, String fifty, String fiftyOne, String fiftyTwo, String fiftyThree, String createdTime) {
        this.number = number;
        this.name = name;
        this.unit = unit;
        this.post = post;
        this.sex = sex;
        this.status = status;
        this.degree = degree;
        this.age = age;
        this.seniority = seniority;
        this.one = one;
        this.two = two;
        this.three = three;
        this.four = four;
        this.five = five;
        this.six = six;
        this.seven = seven;
        this.eight = eight;
        this.nine = nine;
        this.ten = ten;
        this.eleven = eleven;
        this.twelve = twelve;
        this.thirteen = thirteen;
        this.fourteen = fourteen;
        this.fifteen = fifteen;
        this.sixteen = sixteen;
        this.seventeen = seventeen;
        this.eighteen = eighteen;
        this.nineteen = nineteen;
        this.twenty = twenty;
        this.twentyOne = twentyOne;
        this.twentyTwo = twentyTwo;
        this.twentyThree = twentyThree;
        this.twentyFour = twentyFour;
        this.twentyFive = twentyFive;
        this.twentySix = twentySix;
        this.twentySeven = twentySeven;
        this.twentyEight = twentyEight;
        this.twentyNine = twentyNine;
        this.thirty = thirty;
        this.thirtyOne = thirtyOne;
        this.thirtyTwo = thirtyTwo;
        this.thirtyThree = thirtyThree;
        this.thirtyFour = thirtyFour;
        this.thirtyFive = thirtyFive;
        this.thirtySix = thirtySix;
        this.thirtySeven = thirtySeven;
        this.thirtyEight = thirtyEight;
        this.thirtyNine = thirtyNine;
        this.forty = forty;
        this.fortyOne = fortyOne;
        this.fortyTwo = fortyTwo;
        this.fortyThree = fortyThree;
        this.fortyFour = fortyFour;
        this.fortyFive = fortyFive;
        this.fortySix = fortySix;
        this.fortySeven = fortySeven;
        this.fortyEight = fortyEight;
        this.fortyNine = fortyNine;
        this.fifty = fifty;
        this.fiftyOne = fiftyOne;
        this.fiftyTwo = fiftyTwo;
        this.fiftyThree = fiftyThree;
        this.createdTime = createdTime;
    }

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

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;

        Employee employee = (Employee) o;

        if (getId() != null ? !getId().equals(employee.getId()) : employee.getId() != null) return false;
        if (getNumber() != null ? !getNumber().equals(employee.getNumber()) : employee.getNumber() != null)
            return false;
        if (getName() != null ? !getName().equals(employee.getName()) : employee.getName() != null) return false;
        if (getUnit() != null ? !getUnit().equals(employee.getUnit()) : employee.getUnit() != null) return false;
        if (getPost() != null ? !getPost().equals(employee.getPost()) : employee.getPost() != null) return false;
        if (getSex() != null ? !getSex().equals(employee.getSex()) : employee.getSex() != null) return false;
        if (getStatus() != null ? !getStatus().equals(employee.getStatus()) : employee.getStatus() != null)
            return false;
        if (getDegree() != null ? !getDegree().equals(employee.getDegree()) : employee.getDegree() != null)
            return false;
        if (getAge() != null ? !getAge().equals(employee.getAge()) : employee.getAge() != null) return false;
        if (getSeniority() != null ? !getSeniority().equals(employee.getSeniority()) : employee.getSeniority() != null)
            return false;
        if (getOne() != null ? !getOne().equals(employee.getOne()) : employee.getOne() != null) return false;
        if (getTwo() != null ? !getTwo().equals(employee.getTwo()) : employee.getTwo() != null) return false;
        if (getThree() != null ? !getThree().equals(employee.getThree()) : employee.getThree() != null) return false;
        if (getFour() != null ? !getFour().equals(employee.getFour()) : employee.getFour() != null) return false;
        if (getFive() != null ? !getFive().equals(employee.getFive()) : employee.getFive() != null) return false;
        if (getSix() != null ? !getSix().equals(employee.getSix()) : employee.getSix() != null) return false;
        if (getSeven() != null ? !getSeven().equals(employee.getSeven()) : employee.getSeven() != null) return false;
        if (getEight() != null ? !getEight().equals(employee.getEight()) : employee.getEight() != null) return false;
        if (getNine() != null ? !getNine().equals(employee.getNine()) : employee.getNine() != null) return false;
        if (getTen() != null ? !getTen().equals(employee.getTen()) : employee.getTen() != null) return false;
        if (getEleven() != null ? !getEleven().equals(employee.getEleven()) : employee.getEleven() != null)
            return false;
        if (getTwelve() != null ? !getTwelve().equals(employee.getTwelve()) : employee.getTwelve() != null)
            return false;
        if (getThirteen() != null ? !getThirteen().equals(employee.getThirteen()) : employee.getThirteen() != null)
            return false;
        if (getFourteen() != null ? !getFourteen().equals(employee.getFourteen()) : employee.getFourteen() != null)
            return false;
        if (getFifteen() != null ? !getFifteen().equals(employee.getFifteen()) : employee.getFifteen() != null)
            return false;
        if (getSixteen() != null ? !getSixteen().equals(employee.getSixteen()) : employee.getSixteen() != null)
            return false;
        if (getSeventeen() != null ? !getSeventeen().equals(employee.getSeventeen()) : employee.getSeventeen() != null)
            return false;
        if (getEighteen() != null ? !getEighteen().equals(employee.getEighteen()) : employee.getEighteen() != null)
            return false;
        if (getNineteen() != null ? !getNineteen().equals(employee.getNineteen()) : employee.getNineteen() != null)
            return false;
        if (getTwenty() != null ? !getTwenty().equals(employee.getTwenty()) : employee.getTwenty() != null)
            return false;
        if (getTwentyOne() != null ? !getTwentyOne().equals(employee.getTwentyOne()) : employee.getTwentyOne() != null)
            return false;
        if (getTwentyTwo() != null ? !getTwentyTwo().equals(employee.getTwentyTwo()) : employee.getTwentyTwo() != null)
            return false;
        if (getTwentyThree() != null ? !getTwentyThree().equals(employee.getTwentyThree()) : employee.getTwentyThree() != null)
            return false;
        if (getTwentyFour() != null ? !getTwentyFour().equals(employee.getTwentyFour()) : employee.getTwentyFour() != null)
            return false;
        if (getTwentyFive() != null ? !getTwentyFive().equals(employee.getTwentyFive()) : employee.getTwentyFive() != null)
            return false;
        if (getTwentySix() != null ? !getTwentySix().equals(employee.getTwentySix()) : employee.getTwentySix() != null)
            return false;
        if (getTwentySeven() != null ? !getTwentySeven().equals(employee.getTwentySeven()) : employee.getTwentySeven() != null)
            return false;
        if (getTwentyEight() != null ? !getTwentyEight().equals(employee.getTwentyEight()) : employee.getTwentyEight() != null)
            return false;
        if (getTwentyNine() != null ? !getTwentyNine().equals(employee.getTwentyNine()) : employee.getTwentyNine() != null)
            return false;
        if (getThirty() != null ? !getThirty().equals(employee.getThirty()) : employee.getThirty() != null)
            return false;
        if (getThirtyOne() != null ? !getThirtyOne().equals(employee.getThirtyOne()) : employee.getThirtyOne() != null)
            return false;
        if (getThirtyTwo() != null ? !getThirtyTwo().equals(employee.getThirtyTwo()) : employee.getThirtyTwo() != null)
            return false;
        if (getThirtyThree() != null ? !getThirtyThree().equals(employee.getThirtyThree()) : employee.getThirtyThree() != null)
            return false;
        if (getThirtyFour() != null ? !getThirtyFour().equals(employee.getThirtyFour()) : employee.getThirtyFour() != null)
            return false;
        if (getThirtyFive() != null ? !getThirtyFive().equals(employee.getThirtyFive()) : employee.getThirtyFive() != null)
            return false;
        if (getThirtySix() != null ? !getThirtySix().equals(employee.getThirtySix()) : employee.getThirtySix() != null)
            return false;
        if (getThirtySeven() != null ? !getThirtySeven().equals(employee.getThirtySeven()) : employee.getThirtySeven() != null)
            return false;
        if (getThirtyEight() != null ? !getThirtyEight().equals(employee.getThirtyEight()) : employee.getThirtyEight() != null)
            return false;
        if (getThirtyNine() != null ? !getThirtyNine().equals(employee.getThirtyNine()) : employee.getThirtyNine() != null)
            return false;
        if (getForty() != null ? !getForty().equals(employee.getForty()) : employee.getForty() != null) return false;
        if (getFortyOne() != null ? !getFortyOne().equals(employee.getFortyOne()) : employee.getFortyOne() != null)
            return false;
        if (getFortyTwo() != null ? !getFortyTwo().equals(employee.getFortyTwo()) : employee.getFortyTwo() != null)
            return false;
        if (getFortyThree() != null ? !getFortyThree().equals(employee.getFortyThree()) : employee.getFortyThree() != null)
            return false;
        if (getFortyFour() != null ? !getFortyFour().equals(employee.getFortyFour()) : employee.getFortyFour() != null)
            return false;
        if (getFortyFive() != null ? !getFortyFive().equals(employee.getFortyFive()) : employee.getFortyFive() != null)
            return false;
        if (getFortySix() != null ? !getFortySix().equals(employee.getFortySix()) : employee.getFortySix() != null)
            return false;
        if (getFortySeven() != null ? !getFortySeven().equals(employee.getFortySeven()) : employee.getFortySeven() != null)
            return false;
        if (getFortyEight() != null ? !getFortyEight().equals(employee.getFortyEight()) : employee.getFortyEight() != null)
            return false;
        if (getFortyNine() != null ? !getFortyNine().equals(employee.getFortyNine()) : employee.getFortyNine() != null)
            return false;
        if (getFifty() != null ? !getFifty().equals(employee.getFifty()) : employee.getFifty() != null) return false;
        if (getFiftyOne() != null ? !getFiftyOne().equals(employee.getFiftyOne()) : employee.getFiftyOne() != null)
            return false;
        if (getFiftyTwo() != null ? !getFiftyTwo().equals(employee.getFiftyTwo()) : employee.getFiftyTwo() != null)
            return false;
        if (getFiftyThree() != null ? !getFiftyThree().equals(employee.getFiftyThree()) : employee.getFiftyThree() != null)
            return false;
        return getCreatedTime() != null ? getCreatedTime().equals(employee.getCreatedTime()) : employee.getCreatedTime() == null;
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
        result = 31 * result + (getCreatedTime() != null ? getCreatedTime().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Employee{" +
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
                ", createdTime='" + createdTime + '\'' +
                '}';
    }
}
