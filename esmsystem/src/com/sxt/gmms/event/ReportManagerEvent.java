package com.sxt.gmms.event;

import com.sxt.gmms.frame.MainFrame;
import com.sxt.gmms.frame.report.in.InReport;
import com.sxt.gmms.frame.report.sale.SaleReport;
import com.sxt.gmms.frame.report.store.StoredReport;

public class ReportManagerEvent {

	private String event;
	private MainFrame mf;

	public ReportManagerEvent(MainFrame mf, String event) {
		this.event = event;
		this.mf = mf;
	}

	public void doActionEvent() {
		if (event.equals("库存报表")) {
			new StoredReport(mf);
		} else if (event.equals("入库报表")) {
			new InReport(mf);
		} else if (event.equals("销售报表")) {
			new SaleReport(mf);
		} 
	}
}
