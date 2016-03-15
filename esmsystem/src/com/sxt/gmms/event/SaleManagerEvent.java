package com.sxt.gmms.event;

import com.sxt.gmms.frame.MainFrame;
import com.sxt.gmms.frame.sell.sellall.SellAllFrame;
import com.sxt.gmms.frame.sell.sellorder.SellGoodFrame;

public class SaleManagerEvent {

	private String event;
	private MainFrame mf;

	public SaleManagerEvent(MainFrame mf, String event) {
		this.event = event;
		this.mf = mf;
	}

	public void doActionEvent() {
		if (event.equals("销售出库")) {
			try {
				SellGoodFrame sellGoodFrame = new SellGoodFrame(mf);
				mf.desktopPane.add(sellGoodFrame);
				sellGoodFrame.setSelected(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (event.equals("出库汇总")) {
			try {
				SellAllFrame sellAllFrame = new SellAllFrame(mf);
				mf.desktopPane.add(sellAllFrame);
				sellAllFrame.setSelected(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
