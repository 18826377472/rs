package com.cy.rs.entity;


import java.util.Arrays;

/**
 * 验证码类
 */
public class VerifyCode {

    private String code;

    private byte[] imgBytes;

    private long expireTime;


    public VerifyCode() {
    }

    public VerifyCode(String code, byte[] imgBytes, long expireTime) {
        this.code = code;
        this.imgBytes = imgBytes;
        this.expireTime = expireTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public byte[] getImgBytes() {
        return imgBytes;
    }

    public void setImgBytes(byte[] imgBytes) {
        this.imgBytes = imgBytes;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VerifyCode)) return false;

        VerifyCode that = (VerifyCode) o;

        if (getExpireTime() != that.getExpireTime()) return false;
        if (getCode() != null ? !getCode().equals(that.getCode()) : that.getCode() != null) return false;
        return Arrays.equals(getImgBytes(), that.getImgBytes());
    }

    @Override
    public int hashCode() {
        int result = getCode() != null ? getCode().hashCode() : 0;
        result = 31 * result + Arrays.hashCode(getImgBytes());
        result = 31 * result + (int) (getExpireTime() ^ (getExpireTime() >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "VerifyCode{" +
                "code='" + code + '\'' +
                ", imgBytes=" + Arrays.toString(imgBytes) +
                ", expireTime=" + expireTime +
                '}';
    }
}


