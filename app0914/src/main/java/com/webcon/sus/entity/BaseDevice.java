package com.webcon.sus.entity;

import android.support.annotation.NonNull;

import com.webcon.sus.utils.SUConstant;

/**
 * 设备
 * @author m
 */
public class BaseDevice implements Comparable<BaseDevice>{

    /** 所属站场ID */
    private int mStationId;
    /** 设备ID */// **保留。。
    private String mId;
    /** 设备名称 */
    private String mName;
    /** 设备类型 */
    private final int mType;
    /** 备注信息(保留) */
    private String mRemark;
    /** 是否可用  */// **保留。。
    private boolean isAvailable;
    /** 是否有报警 */// **保留。。
    private boolean isAlarming;

    public BaseDevice(int type){
        this.mType = type;
    }

    @Override
    public int compareTo(@NonNull BaseDevice another) {//默认升序？ -1 0 1
        //##大概以后还可以添加一些比较
        //TODO: 注：为了将摄像头排在最前面，暂时如此处理 ...
        if(mType == SUConstant.DEVICE_TYPE_MONITOR){
            if(another.getType() == SUConstant.DEVICE_TYPE_MONITOR){
                return 0;
            }else{
                return -1;
            }
        }else if(another.getType() == SUConstant.DEVICE_TYPE_MONITOR){
            return 1;
        }else{
            if(another.getType() < mType){//序号小的排前？
                return -1;
            }else if(another.getType() > mType){
                return 1;
            }else{
                return 0;
            }
        }
    }

    public int getStationId() {
        return mStationId;
    }

    public void setStationId(int mStationId) {
        this.mStationId = mStationId;
    }

    /**
     * 不要用mId相关的数据，使用mName作为唯一标识符
     * <br>使用{@link BaseDevice#getName()}
     */
    @Deprecated
    public String getID() {
        return mId;
    }

    /**
     * 不要用mId相关的数据，使用mName作为唯一标识符
     * <br>使用{@link BaseDevice#setName(String)} ()}
     */
    @Deprecated
    public void setID(String mId) {
        this.mId = mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public int getType(){
        return this.mType;
    }

    public String getRemark() {
        return mRemark;
    }

    public void setRemark(String mRemark) {
        this.mRemark = mRemark;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public boolean isAlarming() {
        return isAlarming;
    }

    public void setIsAlarming(boolean isAlarming) {
        this.isAlarming = isAlarming;
    }
}
