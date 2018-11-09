package cn.finwood.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

/**
 * 请求参数DTO
 * created by haoyanbing on 2018/11/9 11:21
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParamDto {
    private Long sysuserId;
    private String sysuserName;
    private int userType;
    private String clientIp;

    private Long createdBy;
    private String createdByName;
    private Integer creatorType;
    private String createdIp;

    private Long lastModifiedBy;
    private String lastModifiedByName;
    private Integer lastModifierType;
    private Date lastModifiedTime;
    private String lastModifiedIp;

    public void setSysuserId(Long sysuserId) {
        this.sysuserId = sysuserId;
        this.createdBy=this.sysuserId;
        this.lastModifiedBy=this.sysuserId;
    }

    public void setSysuserName(String sysuserName) {
        this.sysuserName = sysuserName;
        this.createdByName=this.sysuserName;
        this.lastModifiedByName=this.sysuserName;
    }

    public void setUserType(int userType) {
        this.userType = userType;
        this.creatorType=this.userType;
        this.lastModifierType=this.userType;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
        this.createdIp=this.clientIp;
        this.lastModifiedIp=this.clientIp;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }



    public Long getSysuserId() {
        return sysuserId;
    }

    public String getSysuserName() {
        return sysuserName;
    }

    public int getUserType() {
        return userType;
    }

    public String getClientIp() {
        return clientIp;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public Integer getCreatorType() {
        return creatorType;
    }

    public String getCreatedIp() {
        return createdIp;
    }

    public Long getLastModifiedBy() {
        return lastModifiedBy;
    }

    public String getLastModifiedByName() {
        return lastModifiedByName;
    }

    public Integer getLastModifierType() {
        return lastModifierType;
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public String getLastModifiedIp() {
        return lastModifiedIp;
    }

}
