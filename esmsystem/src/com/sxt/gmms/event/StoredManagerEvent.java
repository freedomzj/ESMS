package com.sxt.gmms.event;

import com.sxt.gmms.frame.MainFrame;
import com.sxt.gmms.frame.stored.all.CheckItemAll;
import com.sxt.gmms.frame.stored.call.CallSetFrame;
import com.sxt.gmms.frame.stored.check.ShowStoredFrame;

public class StoredManagerEvent {
	private String event;
	private MainFrame mf;

	public StoredManagerEvent(MainFrame mf, String event) {
		this.event = event;
		this.mf = mf;
	}

	public void doActionEvent() {
		if (event.equals("库存报警")) {
			try {
				CallSetFrame callFrame = new CallSetFrame(mf);
				mf.desktopPane.add(callFrame);
				callFrame.setSelected(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (event.equals("盘点汇总")) {
			System.out.println("pandianhuiozng");
			try {
				CheckItemAll checkItemAll = new CheckItemAll(mf);
				mf.desktopPane.add(checkItemAll);
				checkItemAll.setSelected(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (event.equals("库存盘点")) {
			try {
				ShowStoredFrame sellGoodFrame = new ShowStoredFrame(mf);
				mf.desktopPane.add(sellGoodFrame);
				sellGoodFrame.setSelected(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}