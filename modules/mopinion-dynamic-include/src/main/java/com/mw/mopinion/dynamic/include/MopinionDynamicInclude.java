package com.mw.mopinion.dynamic.include;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.taglib.DynamicInclude;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.util.PropsUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

@Component(
	    immediate = true,
	    service = DynamicInclude.class
)
public class MopinionDynamicInclude implements DynamicInclude {
	
	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		if (_log.isInfoEnabled()) _log.info("activated");
	}
	
    @Override
    public void include(HttpServletRequest request, HttpServletResponse response, String key)
            throws IOException {
        PrintWriter out = response.getWriter();
       
        boolean mopinionEnabled = GetterUtil.getBoolean(PropsUtil.get("custom.mopinion.enabled"), false); // TODO This should be moved to a custom configuration
        
        if (mopinionEnabled) {
        	String mopinionId = PropsUtil.get("custom.mopinion.id"); // TODO This should be moved to a custom configuration
        	
            if (Validator.isNotNull(mopinionId)) {
        		ThemeDisplay themeDisplay =
        				(ThemeDisplay)request.getAttribute(
        					WebKeys.THEME_DISPLAY);
                
        		if (Validator.isNotNull(themeDisplay) && themeDisplay.isSignedIn()) {
                    out.println("<!-- Mopinion Pastea.se  start -->");
                    out.println("<script type=\"text/javascript\">(function(){var id=\"" + mopinionId + "\";var js=document.createElement(\"script\");js.setAttribute(\"type\",\"text/javascript\");js.setAttribute(\"src\",\"https://deploy.mopinion.com/js/pastease.js\");js.async=true;document.getElementsByTagName(\"head\")[0].appendChild(js);var t=setInterval(function(){try{Pastease.load(id);clearInterval(t)}catch(e){}},50)})();</script>");
                    out.println("<!-- Mopinion Pastea.se end -->");         			
        		}            	
            }
        }
    }

	@Override
	public void register(DynamicIncludeRegistry dynamicIncludeRegistry) {
		dynamicIncludeRegistry.register(
			"/html/common/themes/top_head.jsp#post");
	}   

    private static final Log _log =
            LogFactoryUtil.getLog(MopinionDynamicInclude.class);
}