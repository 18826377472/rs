//package com.cy.rs.mapper;
//
//import com.cy.rs.entity.Employee;
//import com.cy.rs.entity.Shuju;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class ShujuMapperTests {
//
//    @Autowired(required = false)
//    private ShujuMapper shujuMapper;
//
//    /**
//     * 终端专员匹配系数计算
//     * performance 绩效 0.20 82.984-87(0.08) 87-91(0.12) 91-95(0.16) 95-99(0.20)
//     * one 是否有体育特长 0.11 是(0.11)  否(0.06)
//     * nineteen 是否具有中级以上计算机方面的资格证书 0.08  是(0.08)  否(0.04)
//     * twenty 是否有参加市局组织的新媒体培训经历 0.07   是(0.07)  否(0.04)
//     * thirty 是否新媒体营销团队成员 0.12  是(0.12)  否(0.06)
//     * thirtyFour 当地主要使用的方言掌握情况  0.04 会说(0.04)  能听不会说(0.03)  基本听不懂(0.02)
//     * thirtySix 粤语掌握情况  0.16  会说(0.16)  能听不会说(0.12)  基本听不懂(0.08)
//     * thirtyNine 是否在工作地（区/县/市）定居 0.05  是(0.05)  否(0.03)
//     * fortyFive 是否有论文发表或获奖情况 0.11   是(0.11)  否(0.06)
//     * fortyNine 是否有参与的视频项目并在省局以上媒体发表情况  0.06  是(0.06)  否(0.03)
//     */
//    @Test
//    public void terminalSpecialist(){
//        List<Shuju> select = shujuMapper.selectByCeshi12();
//        for (Shuju s : select) {
//            double sum =0;
//            String one = s.getOne();
//            String nineteen = s.getNineteen();
//            String twenty = s.getTwenty();
//            String thirty = s.getThirty();
//            String thirtyFour = s.getThirtyFour();
//            String thirtySix = s.getThirtySix();
//            String thirtyNine = s.getThirtyNine();
//            String fortyFive = s.getFortyFive();
//            String fortyNine = s.getFortyNine();
//            Double performance = s.getPerformance();
//
//            if(one.equals("是")){
//                sum+=0.11;
//            }else {
//                sum+=0.06;
//            }
//
//
//            if(nineteen.equals("是")){
//                sum+=0.08;
//            }else {
//                sum+=0.04;
//            }
//
//
//            if(twenty.equals("是")){
//                sum+=0.07;
//            }else {
//                sum+=0.04;
//            }
//
//
//            if(thirty.equals("是")){
//                sum+=0.12;
//            }else{
//                sum+=0.06;
//            }
//
//
//            if(thirtyFour.equals("会说")){
//                sum+=0.04;
//            }else if (thirtyFour.equals("能听不会说")){
//                sum+=0.03;
//            }else if(thirtyFour.equals("基本听不懂")){
//                sum+=0.02;
//            }
//
//
//            if(thirtySix.equals("会说")){
//                sum+=0.16;
//            }else if (thirtySix.equals("能听不会说")){
//                sum+=0.12;
//            }else if(thirtySix.equals("基本听不懂")){
//                sum+=0.08;
//            }
//
//
//            if(thirtyNine.equals("是")){
//                sum+=0.05;
//            }else {
//                sum+=0.03;
//            }
//
//
//            if(fortyFive.equals("是")){
//                sum+=0.11;
//            }else {
//                sum+=0.06;
//            }
//
//
//            if(fortyNine.equals("是")){
//                sum+=0.06;
//            }else {
//                sum+=0.03;
//            }
//
//
//            if (performance<=87){
//                sum+=0.08;
//            }else if (performance<=91 && performance>87){
//                sum+=0.12;
//            }else if(performance<=95 && performance>91){
//                sum+=0.16;
//            }else if(performance>95){
//                sum+=0.20;
//            }
//            System.out.println(sum);
//        }
//
//    }
//
//    /**
//     * 客户专员匹配系数计算
//     * degree 最高学历  0.10 大学(0.10) 大专(0.08) 高中(0.06) 中专、初级、技校(0.04)
//     * age 年龄 0.05  22.966-31.5(0.02) 31.5-40(0.03) 40-48.5(0.04) 48.5-57(0.05)
//     * seniority 工作年龄 0.19  -0.01-9(0.07) 9-16(0.11)  16-22(0.15) 22-(0.19)
//     * performance月度绩效  0.22     82.984-87(0.10) 87-91(0.14) 91-95(0.18) 95-99(0.22)
//     * seven 新媒体营销技术水平  0.08     很好 较好(0.08)  一般(0.05) 较差(0.03)
//     * nineteen 是否具有中级以上计算机方面的资格证书 0.03    是(0.03)  否(0.02)
//     * thirty 是否新媒体营销团队成员  0.09  是(0.09) 否(0.05)
//     * forty是否在工作地（区/县/市）定居 0.08  是(0.08) 否(0.04)
//     * forty_one 是否有任职营销以外岗位的工作经历 0.04   是(0.04) 否(0.02)
//     * forty_three 生育情况 0.12  无(0.06) 一孩(0.08)  二孩(0.10)  三孩(0.12)
//     */
//    @Test
//    public void accountSpecialist(){
//        List<Shuju> select = shujuMapper.selectByCeshi12();
//        for (Shuju s : select) {
//            Double sum =0.0;
//            String degree = s.getDegree();
//            Integer age = s.getAge();
//            Integer seniority = s.getSeniority();
//            Double performance = s.getPerformance();
//            String seven = s.getSeven();
//            String nineteen = s.getNineteen();
//            String thirty = s.getThirty();
//            String forty = s.getForty();
//            String fortyOne = s.getFortyOne();
//            String fortyThree = s.getFortyThree();
//
//            if (degree.equals("大学")){
//                sum+=0.10;
//            }else if (degree.equals("大专")){
//                sum+=0.08;
//            }else if (degree.equals("高中")){
//                sum+=0.06;
//            }else if (degree.equals("初中")){
//                sum+=0.04;
//            }else if (degree.equals("技校")){
//                sum+=0.04;
//            }else{
//                sum+=0.04;
//            }
//
//            if (age<=31.5){
//                sum+=0.02;
//            }else if (age>31.5 && age<=40){
//                sum+=0.03;
//            }else if (age>40 && age<=48.5){
//                sum+=0.04;
//            }else if (age>48.5){
//                sum+=0.05;
//            }
//
//            if (seniority<=9){
//                sum+=0.07;
//            }else if (seniority>9 && seniority<=16){
//                sum+=0.11;
//            }else if (seniority>16 && seniority<=22){
//                sum+=0.15;
//            }else if (seniority>22){
//                sum+=0.19;
//            }
//
//            if (performance<=87){
//                sum+=0.10;
//            }else if (performance>87 && performance<=91){
//                sum+=0.14;
//            }else if (performance>91 && performance<=95){
//                sum+=0.18;
//            }else if (performance>95){
//                sum+=0.22;
//            }
//
//            if (seven.equals("很好")){
//                sum+=0.08;
//            }else if (seven.equals("较好")){
//                sum+=0.08;
//            }else if (seven.equals("一般")){
//                sum+=0.05;
//            }else {
//                sum+=0.03;
//            }
//
//            if (nineteen.equals("是")){
//                sum+=0.03;
//            }else{
//                sum+=0.02;
//            }
//
//            if (thirty.equals("是")){
//                sum+=0.09;
//            }else{
//                sum+=0.05;
//            }
//
//            if (forty.equals("是")){
//                sum+=0.08;
//            }else{
//                sum+=0.04;
//            }
//
//            if (fortyOne.equals("是")){
//                sum+=0.04;
//            }else{
//                sum+=0.02;
//            }
//
//            if(fortyThree.equals("无")){
//                sum+=0.06;
//            }else if (fortyThree.equals("一孩")){
//                sum+=0.08;
//            }else if(fortyThree.equals("二孩")){
//                sum+=0.10;
//            }else {
//                sum+=0.12;
//            }
//            System.out.println(sum);
//        }
//    }
//
//    /**
//     * 综合管理员匹配系数计算
//     * performance 绩效 0.25   82.984-87(0.16) 87-91(0.19) 91-95(0.22) 95-99(0.25)
//     * age  年龄 0.18  22.966-31.5(0.09) 31.5-40(0.12) 40-48.5(0.15) 48.5-57(0.18)
//     * four 是否艺术类兴趣小组成员 0.15 是(0.15) 否(0.08)
//     * seventeen 是否四级烟草制品购销职业资格（最高级别) 0.10 是(0.10)  否(0.06)
//     * eighteen 是否五级烟草制品购销职业资格（最高级别） 0.07  是(0.07)  否(0.04)
//     * nineteen 是否具有中级以上计算机方面的资格证书 0.07   是(0.07)  否(0.04)
//     * thirteen 是否中级或以上经济师 0.06   是(0.06)  否(0.04)
//     * eleven 营销策划及执行能力 0.04 很好较好(0.04)  一般(0.03)  较差(0.02)
//     * twentyOne 是否有参加市局组织的数据分析培训经历  0.04   是(0.04)  否(0.02)
//     * twelve 团队意识及协作能力  0.04   很好较好(0.04)  一般(0.03)  较差(0.02)
//     */
//    @Test
//    public void comprehensiveAdministrator(){
//        List<Shuju> select = shujuMapper.selectByCeshi12();
//        for (Shuju s : select) {
//            Double sum = 0.0;
//            Double performance = s.getPerformance();
//            Integer age = s.getAge();
//            String four = s.getFour();
//            String seventeen = s.getSeventeen();
//            String eighteen = s.getEighteen();
//            String nineteen = s.getNineteen();
//            String thirteen = s.getThirteen();
//            String eleven = s.getEleven();
//            String twentyOne = s.getTwentyOne();
//            String twelve = s.getTwelve();
//
//            if (performance<=87){
//                sum+=0.16;
//            }else if (performance>87 && performance<=91){
//                sum+=0.19;
//            }else if (performance>91 && performance<=95){
//                sum+=0.22;
//            }else if (performance>95){
//                sum+=0.25;
//            }
//
//            if (age<=31.5){
//                sum+=0.09;
//            }else if (age>31.5 && age<=40){
//                sum+=0.12;
//            }else if (age>40 && age<=48.5){
//                sum+=0.15;
//            }else if (age>48.5){
//                sum+=0.18;
//            }
//
//            if (four.equals("是")){
//                sum+=0.15;
//            }else{
//                sum+=0.08;
//            }
//
//            if (seventeen.equals("是")){
//                sum+=0.10;
//            }else{
//                sum+=0.06;
//            }
//
//            if (eighteen.equals("是")){
//                sum+=0.07;
//            }else{
//                sum+=0.04;
//            }
//
//            if (nineteen.equals("是")){
//                sum+=0.07;
//            }else{
//                sum+=0.04;
//            }
//
//            if (thirteen.equals("是")){
//                sum+=0.06;
//            }else{
//                sum+=0.04;
//            }
//
//            if(eleven.equals("很好")){
//                sum+=0.04;
//            }else if (eleven.equals("较好")){
//                sum+=0.04;
//            }else if (eleven.equals("一般")){
//                sum+=0.03;
//            }else{
//                sum+=0.02;
//            }
//
//            if (twentyOne.equals("是")){
//                sum+=0.04;
//            }else {
//                sum+=0.02;
//            }
//
//            if (twelve.equals("很好")){
//                sum+=0.04;
//            }else if (twelve.equals("较好")){
//                sum+=0.04;
//            }else if (twelve.equals("一般")){
//                sum+=0.03;
//            }else {
//                sum+=0.02;
//            }
//            System.out.println(sum);
//        }
//    }
//
//    /**
//     * 信息专员匹配系数计算
//     * age 年龄  0.17     22.966-31.5(0.11) 31.5-40(0.13) 40-48.5(0.15) 48.5-57(0.17)
//     * fortyFive  是否有论文发表或获奖情况 0.14    是(0.14)  否(0.10)
//     * seniority 工龄 0.11   -0.01-9(0.05) 9-16(0.07)  16-22(0.09) 22-(0.11)
//     * twelve  团队意识及协作能力  0.10   很好较好(0.10)  一般(0.08)  较差(0.06)
//     * senventeen 是否四级烟草制品购销职业资格（最高级别)  0.08   是(0.08)  否(0.05)
//     * twentynine  是否数据分析团队成员  0.08    是(0.08)  否(0.05)
//     * fourteen  是否初级经济师  0.08    是(0.08)  否(0.05)
//     * fortyTwo 是否已婚  0.06    是(0.06)  否(0.03)
//     * fortyOne  是否有任职当前岗位以外营销岗位的工作经历（客户专员、信息专员、终端专员原任客户经理的，不视作不同岗位）  0.05    是(0.05)  否(0.03)
//     * performance  绩效  0.13  82.984-87(0.07) 87-91(0.09) 91-95(0.11) 95-99(0.13)
//     */
//    @Test
//    public void informationCommissioner(){
//        List<Shuju> select = shujuMapper.selectByCeshi12();
//        for (Shuju s : select) {
//            Double sum = 0.0;
//            Integer age = s.getAge();
//            String fortyFive = s.getFortyFive();
//            Integer seniority = s.getSeniority();
//            String twelve = s.getTwelve();
//            String seventeen = s.getSeventeen();
//            String twentyNine = s.getTwentyNine();
//            String fourteen = s.getFourteen();
//            String fortyTwo = s.getFortyTwo();
//            String fortyOne = s.getFortyOne();
//            Double performance = s.getPerformance();
//
//            if (age<=31.5){
//                sum+=0.11;
//            }else if (age>31.5 && age<=40){
//                sum+=0.13;
//            }else if (age>40 && age<=48.5){
//                sum+=0.15;
//            }else if (age>48.5){
//                sum+=0.17;
//            }
//
//            if (fortyFive.equals("是")){
//                sum+=0.14;
//            }else{
//                sum+=0.10;
//            }
//
//            if (seniority<=9){
//                sum+=0.05;
//            }else if (seniority>9 && seniority<=16){
//                sum+=0.07;
//            }else if (seniority>16 && seniority<=22){
//                sum+=0.09;
//            }else if (seniority>22){
//                sum+=0.11;
//            }
//
//            if (twelve.equals("很好")){
//                sum+=0.10;
//            }else if (twelve.equals("较好")){
//                sum+=0.010;
//            }else if (twelve.equals("一般")){
//                sum+=0.08;
//            }else {
//                sum+=0.06;
//            }
//
//            if (seventeen.equals("是")){
//                sum+=0.08;
//            }else{
//                sum+=0.05;
//            }
//
//            if (twentyNine.equals("是")){
//                sum+=0.08;
//            }else{
//                sum+=0.05;
//            }
//
//            if (fourteen.equals("是")){
//                sum+=0.08;
//            }else{
//                sum+=0.05;
//            }
//
//            if (fortyTwo.equals("是")){
//                sum+=0.06;
//            }else{
//                sum+=0.03;
//            }
//
//            if (fortyOne.equals("是")){
//                sum+=0.05;
//            }else{
//                sum+=0.03;
//            }
//
//            if (performance<=87){
//                sum+=0.07;
//            }else if (performance>87 && performance<=91){
//                sum+=0.09;
//            }else if (performance>91 && performance<=95){
//                sum+=0.11;
//            }else if (performance>95){
//                sum+=0.13;
//            }
//            System.out.println(sum);
//        }
//    }
//
//    /**
//     * 市场经理匹配系数计算
//     * thirteen    0.26  是否中级或以上经济师  是(0.26)  否(0.20)
//     * eighteen    0.25  是否五级烟草制品购销职业资格（最高级别）  是(0.20)  否(0.15)
//     * fortyfive   0.10  是否有论文发表或获奖情况   是(0.10)  否(0.06)
//     * fortyfour   0.10  是否有QC项目获奖情况    是(0.10)  否(0.06)
//     * fortytwo    0.10  是否已婚   是(0.10)  否(0.06)
//     * performance 0.08  绩效   82.984-87(0.02) 87-91(0.04) 91-95(0.06) 95-99(0.08)
//     * fortyseven  0.05  是否有参与数字化转型项目情况    是(0.05)  否(0.03)
//     * five        0.03  公文写作能力   很好较好(0.03)  一般(0.02)  较差(0.01)
//     * thirtyfour  0.03  当地主要使用的方言掌握情况  会说(0.03)  能听不会说(0.02)  基本听不懂(0.01)
//     */
//    @Test
//    public void marketingManager(){
//        List<Shuju> select = shujuMapper.selectByCeshi12();
//        for (Shuju s : select) {
//            Double sum = 0.0;
//            String thirteen = s.getThirteen();
//            String eighteen = s.getEighteen();
//            String fortyFive = s.getFortyFive();
//            String fortyFour = s.getFortyFour();
//            String fortyTwo = s.getFortyTwo();
//            Double performance = s.getPerformance();
//            String fortySeven = s.getFortySeven();
//            String five = s.getFive();
//            String thirtyFour = s.getThirtyFour();
//
//            if (thirteen.equals("是")){
//                sum+=0.26;
//            }else{
//                sum+=0.20;
//            }
//
//            if (eighteen.equals("是")){
//                sum+=0.20;
//            }else{
//                sum+=0.15;
//            }
//
//            if (fortyFive.equals("是")){
//                sum+=0.10;
//            }else{
//                sum+=0.06;
//            }
//
//            if (fortyFour.equals("是")){
//                sum+=0.10;
//            }else{
//                sum+=0.06;
//            }
//
//            if (fortyTwo.equals("是")){
//                sum+=0.10;
//            }else{
//                sum+=0.06;
//            }
//
//            if (performance<=87){
//                sum+=0.02;
//            }else if (performance>87 && performance<=91){
//                sum+=0.04;
//            }else if (performance>91 && performance<=95){
//                sum+=0.06;
//            }else if (performance>95){
//                sum+=0.08;
//            }
//
//            if (fortySeven.equals("是")){
//                sum+=0.05;
//            }else{
//                sum+=0.03;
//            }
//
//            if (five.equals("很好")){
//                sum+=0.03;
//            }else if (five.equals("较好")){
//                sum+=0.03;
//            }else if (five.equals("一般")){
//                sum+=0.02;
//            }else {
//                sum+=0.01;
//            }
//
//            if(thirtyFour.equals("会说")){
//                sum+=0.03;
//            }else if (thirtyFour.equals("能听不会说")){
//                sum+=0.02;
//            }else if(thirtyFour.equals("基本听不懂")){
//                sum+=0.01;
//            }
//            System.out.println(sum);
//        }
//    }
//
//    /**
//     * 客户专员陈老师新算法
//     * degree 0.09 最高学历  大学0.09 大专0.07 高中0.05 初中技校中专0.03
//     * five 0.10 公共写作能力  很好0.10 较好0.08 一般0.06 较差0。04
//     * twentyOne 0.09 是否有参加市局组织的数据分析培训经历 是(0.09) 否(0.06)
//     * twentyFive 0.10 是否市级或县级内训师  是(0.10) 否(0.07)
//     * twentySeven 0.10 近两年是否有参与内训师相关竞赛的经历  是(0.10) 否(0.07)
//     * twentyNine 0.12 是否数据分析团队成员  是(0.12) 否(0.09)
//     * thirtyTwo 0.13  是否省局创客工作室成员  是(0.13) 否(0.10)
//     * thirtyThree 0.10  是否有参加省局专项工作的经历  是(0.10) 否(0.07)
//     * fortyFour 0.08  是否有QC项目获奖情况  是(0.08) 否(0.05)
//     * Fifty  0.09  是否有参与市局营销竞赛并获奖的情况  是(0.09) 否(0.06)
//     */
//    @Test
//    public void accountSpecialistChen(){
//        List<Shuju> select = shujuMapper.selectByCeshi3();
//        for (Shuju s : select) {
//            Double sum = 0.0;
//            String degree = s.getDegree();
//            String five = s.getFive();
//            String twentyOne = s.getTwentyOne();
//            String twentyFive = s.getTwentyFive();
//            String twentySeven = s.getTwentySeven();
//            String twentyNine = s.getTwentyNine();
//            String thirtyTwo = s.getThirtyTwo();
//            String thirtyThree = s.getThirtyThree();
//            String fortyFour = s.getFortyFour();
//            String fifty = s.getFifty();
//            Double performance = s.getPerformance();
//            if (degree.equals("大学")){
//                sum+=0.09;
//            }else if (degree.equals("大专")){
//                sum+=0.07;
//            }else if (degree.equals("高中")){
//                sum+=0.05;
//            }else {
//                sum+=0.03;
//            }
//
//            if (five.equals("很好")){
//                sum+=0.10;
//            }else if (five.equals("较好")){
//                sum+=0.08;
//            }else if (five.equals("一般")){
//                sum+=0.06;
//            }else {
//                sum+=0.04;
//            }
//
//            if (twentyOne.equals("是")){
//                sum+=0.09;
//            }else{
//                sum+=0.06;
//            }
//
//            if (twentyFive.equals("是")){
//                sum+=0.10;
//            }else{
//                sum+=0.07;
//            }
//
//            if (twentySeven.equals("是")){
//                sum+=0.10;
//            }else{
//                sum+=0.07;
//            }
//
//            if (twentyNine.equals("是")){
//                sum+=0.12;
//            }else{
//                sum+=0.09;
//            }
//
//            if (thirtyTwo.equals("是")){
//                sum+=0.12;
//            }else{
//                sum+=0.09;
//            }
//
//            if (thirtyThree.equals("是")){
//                sum+=0.10;
//            }else{
//                sum+=0.07;
//            }
//
//            if (fortyFour.equals("是")){
//                sum+=0.08;
//            }else{
//                sum+=0.05;
//            }
//
//            if (fifty.equals("是")){
//                sum+=0.09;
//            }else{
//                sum+=0.06;
//            }
//
//
//            System.out.println(sum);
//        }
//    }
//    /**
//     * 终端专员新算法
//     * thirteen 0.10 是否中级或以上经济师  是(0.10) 否(0.07)
//     * sixteen 0.09 是否三级烟草制品购销职业资格（最高级别）  是(0.09) 否(0.06)
//     * twenty 0.11 是否有参加市局组织的新媒体培训经历 是(0.11) 否(0.08)
//     * twentyFive 0.08 是否市级或县级内训师  是(0.08) 否(0.05)
//     * twentySix 0.10 近两年是否有参与线下授课经历  是(0.10) 否(0.07)
//     * twentyEight 0.08 近两年是否有参加视频课程开发经历  是(0.08) 否(0.05)
//     * thirtyThree 0.09  是否有参加省局专项工作的经历   是(0.09) 否(0.06)
//     * fortyfour 0.12  是否有QC项目获奖情况  是(0.12) 否(0.09)
//     * fortySix 0.09  是否有文章在省局以上媒体发表情况   是(0.09) 否(0.06)
//     * FiftyTwo  0.15  是否受到省局（公司）表彰  是(0.15) 否(0.12)
//     */
//    @Test
//    public void terminalSpecialistChen(){
//        List<Shuju> select = shujuMapper.selectByCeshi3();
//        for (Shuju s : select) {
//            Double sum = 0.0;
//            String thirteen = s.getThirteen();
//            String sixteen = s.getSixteen();
//            String twenty = s.getTwenty();
//            String twentyFive = s.getTwentyFive();
//            String twentySix = s.getTwentySix();
//            String twentyEight = s.getTwentyEight();
//            String thirtyThree = s.getThirtyThree();
//            String fortyFour = s.getFortyFour();
//            String fortySix = s.getFortySix();
//            String fiftyTwo = s.getFiftyTwo();
//
//            if (thirteen.equals("是")){
//                sum+=0.10;
//            }else{
//                sum+=0.07;
//            }
//
//            if (sixteen.equals("是")){
//                sum+=0.09;
//            }else{
//                sum+=0.06;
//            }
//
//
//            if (twenty.equals("是")){
//                sum+=0.11;
//            }else{
//                sum+=0.08;
//            }
//
//            if (twentyFive.equals("是")){
//                sum+=0.8;
//            }else{
//                sum+=0.05;
//            }
//
//            if (twentySix.equals("是")){
//                sum+=0.10;
//            }else{
//                sum+=0.07;
//            }
//
//            if (twentyEight.equals("是")){
//                sum+=0.8;
//            }else{
//                sum+=0.05;
//            }
//
//            if (thirtyThree.equals("是")){
//                sum+=0.9;
//            }else{
//                sum+=0.06;
//            }
//
//            if (fortyFour.equals("是")){
//                sum+=0.12;
//            }else{
//                sum+=0.09;
//            }
//
//            if (fortySix.equals("是")){
//                sum+=0.09;
//            }else{
//                sum+=0.06;
//            }
//
//            if (fiftyTwo.equals("是")){
//                sum+=0.15;
//            }else{
//                sum+=0.12;
//            }
//            System.out.println(sum);
//        }
//    }
//
//    /**
//     *信息专员陈老师新算法
//     * five  0.08  公文写作能力
//     * nine  0.08    创新能力（意识、行为和成效）
//     * eleven  0.08    营销策划及执行能力
//     * teentyThree 0.07  是否有省局轮训经历
//     * twentySix  0.08  近两年是否有参与线下授课经历
//     * twentySeven  0.08  近两年是否有参与内训师相关竞赛的经历
//     * thirty  0.08  是否新媒体营销团队成员
//     * thirtyOne 0.07   是否省局劳模工作室成员
//     * thirtyThree  0.07   是否有参加省局专项工作的经历
//     * fortyEight  0.08  是否有作为主要成员参加营销创新项目的经历
//     * fortyNine  0.07  是否有参与的视频项目并在省局以上媒体发表情况
//     * fifty  0.08  是否有参与市局营销竞赛并获奖的情况
//     * fiftyTwo  0.07  是否受到省局（公司）表彰
//     */
//    @Test
//    public void informationCommissionerChen(){
//        List<Shuju> select = shujuMapper.selectByCeshi3();
//        for (Shuju s : select) {
//            Double sum = 0.0;
//            String degree = s.getDegree();
//            String five = s.getFive();
//            String twentyOne = s.getTwentyOne();
//            String twentyFive = s.getTwentyFive();
//            String twentySeven = s.getTwentySeven();
//            String twentyNine = s.getTwentyNine();
//            String thirtyTwo = s.getThirtyTwo();
//            String thirtyThree = s.getThirtyThree();
//            String fortyFour = s.getFortyFour();
//            String fifty = s.getFifty();
//            Double performance = s.getPerformance();
//            if (degree.equals("大学")){
//                sum+=0.09;
//            }else if (degree.equals("大专")){
//                sum+=0.07;
//            }else if (degree.equals("高中")){
//                sum+=0.05;
//            }else {
//                sum+=0.03;
//            }
//
//            if (five.equals("很好")){
//                sum+=0.10;
//            }else if (five.equals("较好")){
//                sum+=0.08;
//            }else if (five.equals("一般")){
//                sum+=0.06;
//            }else {
//                sum+=0.04;
//            }
//
//            if (twentyOne.equals("是")){
//                sum+=0.09;
//            }else{
//                sum+=0.06;
//            }
//
//            if (twentyFive.equals("是")){
//                sum+=0.10;
//            }else{
//                sum+=0.07;
//            }
//
//            if (twentySeven.equals("是")){
//                sum+=0.10;
//            }else{
//                sum+=0.07;
//            }
//
//            if (twentyNine.equals("是")){
//                sum+=0.12;
//            }else{
//                sum+=0.09;
//            }
//
//            if (thirtyTwo.equals("是")){
//                sum+=0.12;
//            }else{
//                sum+=0.09;
//            }
//
//            if (thirtyThree.equals("是")){
//                sum+=0.10;
//            }else{
//                sum+=0.07;
//            }
//
//            if (fortyFour.equals("是")){
//                sum+=0.08;
//            }else{
//                sum+=0.05;
//            }
//
//            if (fifty.equals("是")){
//                sum+=0.09;
//            }else{
//                sum+=0.06;
//            }
//
//
//            System.out.println(sum);
//        }
//    }
//}
