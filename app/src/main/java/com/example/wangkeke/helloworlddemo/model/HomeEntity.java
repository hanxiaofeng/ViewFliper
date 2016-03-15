package com.example.wangkeke.helloworlddemo.model;

/**
 * Created by wangkeke on 2016/3/4.
 *
 * 右键=--GosnFormat--输入json串--ok
 */
public class HomeEntity {


    /**
     * balance : 15.79
     * flowData : {"total":"2148","unit":"M","used":"0","totalDays":"31","remain":"2148","overDays":"1"}
     * realFee : 56.84
     */

    private String balance;
    /**
     * total : 2148
     * unit : M
     * used : 0
     * totalDays : 31
     * remain : 2148
     * overDays : 1
     */

    private FlowDataEntity flowData;
    private String realFee;

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public void setFlowData(FlowDataEntity flowData) {
        this.flowData = flowData;
    }

    public void setRealFee(String realFee) {
        this.realFee = realFee;
    }

    public String getBalance() {
        return balance;
    }

    public FlowDataEntity getFlowData() {
        return flowData;
    }

    public String getRealFee() {
        return realFee;
    }

    public static class FlowDataEntity {
        private String total;
        private String unit;
        private String used;
        private String totalDays;
        private String remain;
        private String overDays;

        public void setTotal(String total) {
            this.total = total;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public void setUsed(String used) {
            this.used = used;
        }

        public void setTotalDays(String totalDays) {
            this.totalDays = totalDays;
        }

        public void setRemain(String remain) {
            this.remain = remain;
        }

        public void setOverDays(String overDays) {
            this.overDays = overDays;
        }

        public String getTotal() {
            return total;
        }

        public String getUnit() {
            return unit;
        }

        public String getUsed() {
            return used;
        }

        public String getTotalDays() {
            return totalDays;
        }

        public String getRemain() {
            return remain;
        }

        public String getOverDays() {
            return overDays;
        }
    }
}
