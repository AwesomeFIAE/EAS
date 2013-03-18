package bean;

import javax.faces.bean.ManagedBean;

import org.primefaces.context.RequestContext;
import org.primefaces.event.TabChangeEvent;

@ManagedBean
public class TabBean {
	public void onTabChange(TabChangeEvent event) {
		if(event.getTab().getId().equals("loginTab")) {
			RequestContext.getCurrentInstance().execute("dlg.show()");
		}
	}
}
