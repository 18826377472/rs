package com.cy.rs.entity;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;

import java.io.Serializable;
@ExcelIgnoreUnannotated
public class Bill implements Serializable {

    @ExcelProperty("id")
    private Integer id;
    @ExcelProperty("billYear")
    private Integer billYear;
    @ExcelProperty("billMonth")
    private Integer billMonth;
    @ExcelProperty("buildingId")
    private Integer buildingId;
    @ExcelProperty("roomId")
    private Integer roomId;
    @ExcelProperty("waterUsed")
    private Double waterUsed;
    @ExcelProperty("waterFee")
    private Double waterFee;
    @ExcelProperty("energyUsed")
    private Double energyUsed;
    @ExcelProperty("energyFee")
    private Double energyFee;
    @ExcelProperty("totalFee")
    private Double totalFee;
    @ExcelProperty("paid")
    private Integer paid;


        public Bill() {
        }

    public Bill(Integer billYear, Integer billMonth, Integer buildingId, Integer roomId, Double waterUsed, Double waterFee, Double energyUsed, Double energyFee, Double totalFee, Integer paid) {
        this.billYear = billYear;
        this.billMonth = billMonth;
        this.buildingId = buildingId;
        this.roomId = roomId;
        this.waterUsed = waterUsed;
        this.waterFee = waterFee;
        this.energyUsed = energyUsed;
        this.energyFee = energyFee;
        this.totalFee = totalFee;
        this.paid = paid;
    }

    public Bill(Integer id, Integer billYear, Integer billMonth, Integer buildingId, Integer roomId, Double waterUsed, Double waterFee, Double energyUsed, Double energyFee, Double totalFee, Integer paid) {
            this.id = id;
            this.billYear = billYear;
            this.billMonth = billMonth;
            this.buildingId = buildingId;
            this.roomId = roomId;
            this.waterUsed = waterUsed;
            this.waterFee = waterFee;
            this.energyUsed = energyUsed;
            this.energyFee = energyFee;
            this.totalFee = totalFee;
            this.paid = paid;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getBillYear() {
            return billYear;
        }

        public void setBillYear(Integer billYear) {
            this.billYear = billYear;
        }

        public Integer getBillMonth() {
            return billMonth;
        }

        public void setBillMonth(Integer billMonth) {
            this.billMonth = billMonth;
        }

        public Integer getBuildingId() {
            return buildingId;
        }

        public void setBuildingId(Integer buildingId) {
            this.buildingId = buildingId;
        }

        public Integer getRoomId() {
            return roomId;
        }

        public void setRoomId(Integer roomId) {
            this.roomId = roomId;
        }

        public Double getWaterUsed() {
            return waterUsed;
        }

        public void setWaterUsed(Double waterUsed) {
            this.waterUsed = waterUsed;
        }

        public Double getWaterFee() {
            return waterFee;
        }

        public void setWaterFee(Double waterFee) {
            this.waterFee = waterFee;
        }

        public Double getEnergyUsed() {
            return energyUsed;
        }

        public void setEnergyUsed(Double energyUsed) {
            this.energyUsed = energyUsed;
        }

        public Double getEnergyFee() {
            return energyFee;
        }

        public void setEnergyFee(Double energyFee) {
            this.energyFee = energyFee;
        }

        public Double getTotalFee() {
            return totalFee;
        }

        public void setTotalFee(Double totalFee) {
            this.totalFee = totalFee;
        }

        public Integer getPaid() {
            return paid;
        }

        public void setPaid(Integer paid) {
            this.paid = paid;
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bill)) return false;

        Bill bill = (Bill) o;

        if (getId() != null ? !getId().equals(bill.getId()) : bill.getId() != null) return false;
        if (getBillYear() != null ? !getBillYear().equals(bill.getBillYear()) : bill.getBillYear() != null)
            return false;
        if (getBillMonth() != null ? !getBillMonth().equals(bill.getBillMonth()) : bill.getBillMonth() != null)
            return false;
        if (getBuildingId() != null ? !getBuildingId().equals(bill.getBuildingId()) : bill.getBuildingId() != null)
            return false;
        if (getRoomId() != null ? !getRoomId().equals(bill.getRoomId()) : bill.getRoomId() != null) return false;
        if (getWaterUsed() != null ? !getWaterUsed().equals(bill.getWaterUsed()) : bill.getWaterUsed() != null)
            return false;
        if (getWaterFee() != null ? !getWaterFee().equals(bill.getWaterFee()) : bill.getWaterFee() != null)
            return false;
        if (getEnergyUsed() != null ? !getEnergyUsed().equals(bill.getEnergyUsed()) : bill.getEnergyUsed() != null)
            return false;
        if (getEnergyFee() != null ? !getEnergyFee().equals(bill.getEnergyFee()) : bill.getEnergyFee() != null)
            return false;
        if (getTotalFee() != null ? !getTotalFee().equals(bill.getTotalFee()) : bill.getTotalFee() != null)
            return false;
        return getPaid() != null ? getPaid().equals(bill.getPaid()) : bill.getPaid() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getBillYear() != null ? getBillYear().hashCode() : 0);
        result = 31 * result + (getBillMonth() != null ? getBillMonth().hashCode() : 0);
        result = 31 * result + (getBuildingId() != null ? getBuildingId().hashCode() : 0);
        result = 31 * result + (getRoomId() != null ? getRoomId().hashCode() : 0);
        result = 31 * result + (getWaterUsed() != null ? getWaterUsed().hashCode() : 0);
        result = 31 * result + (getWaterFee() != null ? getWaterFee().hashCode() : 0);
        result = 31 * result + (getEnergyUsed() != null ? getEnergyUsed().hashCode() : 0);
        result = 31 * result + (getEnergyFee() != null ? getEnergyFee().hashCode() : 0);
        result = 31 * result + (getTotalFee() != null ? getTotalFee().hashCode() : 0);
        result = 31 * result + (getPaid() != null ? getPaid().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", billYear=" + billYear +
                ", billMonth=" + billMonth +
                ", buildingId=" + buildingId +
                ", roomId=" + roomId +
                ", waterUsed=" + waterUsed +
                ", waterFee=" + waterFee +
                ", energyUsed=" + energyUsed +
                ", energyFee=" + energyFee +
                ", totalFee=" + totalFee +
                ", paid=" + paid +
                '}';
    }
}




