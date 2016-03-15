package com.sxt.gmms.event;

import java.beans.PropertyVetoException;

import com.sxt.gmms.frame.MainFrame;
import com.sxt.gmms.frame.base.customerinfo.CustomerInfoFrame;
import com.sxt.gmms.frame.base.employeeinfo.EmployeeInfoFrame;
import com.sxt.gmms.frame.base.goodsinfo.GoodsInfoFrame;
import com.sxt.gmms.frame.base.goodskind.GoodsKindFrame;
import com.sxt.gmms.frame.base.goodssize.GoodsSizeFrame;
import com.sxt.gmms.frame.base.supplierinfo.SupplierInfoFrame;

public class BaseEvent {

	private String event;
	private MainFrame mf;

	public BaseEvent(MainFrame mf, String event) {
		this.event = event;
		this.mf = mf;
	}

	public void doActionEvent() {
		if (event.equals("商品规格")) {
			try {
				GoodsSizeFrame goodsSizeFrame = new GoodsSizeFrame(mf);
				mf.desktopPane.add(goodsSizeFrame);
				goodsSizeFrame.setSelected(true);
			} catch (PropertyVetoException e1) {
				e1.printStackTrace();
			}
		} else if (event.equals("商品类型")) {
			try {
				GoodsKindFrame goodsKindFrame = new GoodsKindFrame(mf);
				mf.desktopPane.add(goodsKindFrame);
				goodsKindFrame.setSelected(true);
			} catch (PropertyVetoException e1) {
				e1.printStackTrace();
			}
		} else if (event.equals("商品资料")) {
			try {
				GoodsInfoFrame goodsInfoFrame = new GoodsInfoFrame(mf);
				mf.desktopPane.add(goodsInfoFrame);
				goodsInfoFrame.setSelected(true);
			} catch (PropertyVetoException e1) {
				e1.printStackTrace();
			}
		} else if (event.equals("客户资料")) {
			try {
				CustomerInfoFrame memberInfoFrame = new CustomerInfoFrame(mf);
				mf.desktopPane.add(memberInfoFrame);
				memberInfoFrame.setSelected(true);
			} catch (PropertyVetoException e1) {
				e1.printStackTrace();
			}
		} else if (event.equals("供应商资料")) {
			try {
				SupplierInfoFrame supplierInfoFrame = new SupplierInfoFrame(mf);
				mf.desktopPane.add(supplierInfoFrame);
				supplierInfoFrame.setSelected(true);
			} catch (PropertyVetoException e1) {
				e1.printStackTrace();
			}
		} else if (event.equals("员工资料")) {
			try {
				EmployeeInfoFrame employeeInfoFrame = new EmployeeInfoFrame(mf);
				mf.desktopPane.add(employeeInfoFrame);
				employeeInfoFrame.setSelected(true);
			} catch (PropertyVetoException e1) {
				e1.printStackTrace();
			}
		}
	}
}
