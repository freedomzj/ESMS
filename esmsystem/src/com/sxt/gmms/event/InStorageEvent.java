package com.sxt.gmms.event;

import java.beans.PropertyVetoException;

import com.sxt.gmms.frame.MainFrame;
import com.sxt.gmms.frame.instorage.inall.InAllFrame;
import com.sxt.gmms.frame.instorage.instorage.InStorageFrame;

public class InStorageEvent {

	private String event;
	private MainFrame mf;

	public InStorageEvent(MainFrame mf, String event) {
		this.event = event;
		this.mf = mf;
	}

	public void doActionEvent() {
		if (event.equals("采购入库")) {
			try {
				InStorageFrame inStorageFrame = new InStorageFrame(mf);
				mf.desktopPane.add(inStorageFrame);
				inStorageFrame.setSelected(true);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
		} else if (event.equals("入库汇总")) {
			try {
				InAllFrame inAllFrame = new InAllFrame(mf);
				mf.desktopPane.add(inAllFrame);
				inAllFrame.setSelected(true);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
		} else if (event.equals("采购退货")) {

			System.out.println(3);

		} else if (event.equals("退货汇总")) {

			System.out.println(3);

		}
	}
}
