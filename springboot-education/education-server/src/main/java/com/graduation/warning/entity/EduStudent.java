package com.graduation.warning.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 学生信息
 * </p>
 *
 * @author auth
 * @since 2021-03-29
 */
public class EduStudent extends Model<EduStudent> {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
      @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 学号
     */
    private String stuCode;

    /**
     * 姓名
     */
    private String stuName;

    /**
     * 曾用名
     */
    private String stuNameOld;

    /**
     * 姓名拼音
     */
    private String stuNamePy;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 证件类型
     */
    private String certificatType;

    /**
     * 证件号码
     */
    private String certificateNo;

    /**
     * 国籍
     */
    private String nationality;

    /**
     * 籍贯
     */
    private String hometown;

    /**
     * 民族
     */
    private String nation;

    /**
     * 婚姻
     */
    private Integer marriage;

    /**
     * 生源地
     */
    private String birthplace;

    /**
     * 出生日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    /**
     * 政治面貌
     */
    private String politicalStatus;

    /**
     * 手机号
     */
    private String phone;

    /**
     * Email
     */
    private String email;

    /**
     * QQ
     */
    private String qq;

    /**
     * 微信
     */
    @TableField("weChat")
    private String weChat;

    /**
     * 家庭电话
     */
    private String homePhone;

    /**
     * 家庭地址
     */
    private String homeAddress;

    /**
     * 邮政编码
     */
    @TableField("postCode")
    private String postCode;

    /**
     * 特长
     */
    private String skill;

    /**
     * 既往病史
     */
    private String sicknessHistory;

    /**
     * 班级id
     */
    private Integer classId;

    /**
     * 考生号
     */
    private String candidateNo;

    /**
     * 入学时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date admissionTime;

    /**
     * 入学方式
     */
    private String admissionMethord;

    /**
     * 学籍状态
     */
    private String stuStatus;

    /**
     * 培养层次
     */
    private String trainingLevel;

    /**
     * 学制
     */
    private String educationalSystem;

    /**
     * 奖惩情况
     */
    private String rewards;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStuCode() {
        return stuCode;
    }

    public void setStuCode(String stuCode) {
        this.stuCode = stuCode;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuNameOld() {
        return stuNameOld;
    }

    public void setStuNameOld(String stuNameOld) {
        this.stuNameOld = stuNameOld;
    }

    public String getStuNamePy() {
        return stuNamePy;
    }

    public void setStuNamePy(String stuNamePy) {
        this.stuNamePy = stuNamePy;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getCertificatType() {
        return certificatType;
    }

    public void setCertificatType(String certificatType) {
        this.certificatType = certificatType;
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public Integer getMarriage() {
        return marriage;
    }

    public void setMarriage(Integer marriage) {
        this.marriage = marriage;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }



    public String getPoliticalStatus() {
        return politicalStatus;
    }

    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWeChat() {
        return weChat;
    }

    public void setWeChat(String weChat) {
        this.weChat = weChat;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getSicknessHistory() {
        return sicknessHistory;
    }

    public void setSicknessHistory(String sicknessHistory) {
        this.sicknessHistory = sicknessHistory;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getCandidateNo() {
        return candidateNo;
    }

    public void setCandidateNo(String candidateNo) {
        this.candidateNo = candidateNo;
    }


    public String getAdmissionMethord() {
        return admissionMethord;
    }

    public void setAdmissionMethord(String admissionMethord) {
        this.admissionMethord = admissionMethord;
    }

    public String getStuStatus() {
        return stuStatus;
    }

    public void setStuStatus(String stuStatus) {
        this.stuStatus = stuStatus;
    }

    public String getTrainingLevel() {
        return trainingLevel;
    }

    public void setTrainingLevel(String trainingLevel) {
        this.trainingLevel = trainingLevel;
    }

    public String getEducationalSystem() {
        return educationalSystem;
    }

    public void setEducationalSystem(String educationalSystem) {
        this.educationalSystem = educationalSystem;
    }

    public String getRewards() {
        return rewards;
    }

    public void setRewards(String rewards) {
        this.rewards = rewards;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getAdmissionTime() {
        return admissionTime;
    }

    public void setAdmissionTime(Date admissionTime) {
        this.admissionTime = admissionTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "EduStudent{" +
        "id=" + id +
        ", stuCode=" + stuCode +
        ", stuName=" + stuName +
        ", stuNameOld=" + stuNameOld +
        ", stuNamePy=" + stuNamePy +
        ", sex=" + sex +
        ", certificatType=" + certificatType +
        ", certificateNo=" + certificateNo +
        ", nationality=" + nationality +
        ", hometown=" + hometown +
        ", nation=" + nation +
        ", marriage=" + marriage +
        ", birthplace=" + birthplace +
        ", birthday=" + birthday +
        ", politicalStatus=" + politicalStatus +
        ", phone=" + phone +
        ", email=" + email +
        ", qq=" + qq +
        ", weChat=" + weChat +
        ", homePhone=" + homePhone +
        ", homeAddress=" + homeAddress +
        ", postCode=" + postCode +
        ", skill=" + skill +
        ", sicknessHistory=" + sicknessHistory +
        ", classId=" + classId +
        ", candidateNo=" + candidateNo +
        ", admissionTime=" + admissionTime +
        ", admissionMethord=" + admissionMethord +
        ", stuStatus=" + stuStatus +
        ", trainingLevel=" + trainingLevel +
        ", educationalSystem=" + educationalSystem +
        ", rewards=" + rewards +
        "}";
    }
}
