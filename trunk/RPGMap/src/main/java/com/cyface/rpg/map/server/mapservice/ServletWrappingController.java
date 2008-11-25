package com.cyface.rpg.map.server.mapservice;

import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * ServletWrappingController
 *
 * @author Gary Fidler
 * @version $Revision: 1.2 $
 * @created Sep 17, 2008
 * @id $Id: ServletWrappingController.java,v 1.2 2008/09/24 20:03:30 gfidler Exp $
 * @copyright Copyright &#169; 2008 by Qwest Communications, Inc. All Rights
 *            Reserved.
 */
public class ServletWrappingController extends AbstractController implements
        BeanNameAware, InitializingBean, DisposableBean {

    private static Logger log =
        Logger.getLogger(ServletWrappingController.class);

    private Class servletClass;
    private String servletName;
    private Properties initParameters = new Properties();
    private String beanName;
    private Servlet servletInstance;

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws Exception {
        log.debug("afterPropertiesSet()");

        if (servletInstance == null) {
            throw new IllegalArgumentException("servletInstance is required");
        }

        if (!Servlet.class.isAssignableFrom(servletInstance.getClass())) {
            throw new IllegalArgumentException("servletInstance ["
                + servletClass.getName()
                + "] needs to implement interface javax.servlet.Servlet");
        }

        if (servletName == null) {
            servletName = beanName;
        }

        servletInstance.init(new DelegatingServletConfig());
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.beans.factory.DisposableBean#destroy()
     */
    public void destroy() throws Exception {
        log.debug("destroy()");
        servletInstance.destroy();
    }

    /**
     * getBeanName
     *
     * @return Returns the beanName.
     */
    public String getBeanName() {
        return this.beanName;
    }

    /**
     * getInitParameters
     *
     * @return Returns the initParameters.
     */
    public Properties getInitParameters() {
        return this.initParameters;
    }

    /**
     * getServletClass
     *
     * @return Returns the servletClass.
     */
    public Class getServletClass() {
        return this.servletClass;
    }

    /**
     * getServletInstance
     *
     * @return Returns the servletInstance.
     */
    public Servlet getServletInstance() {
        return this.servletInstance;
    }

    /**
     * getServletName
     *
     * @return Returns the servletName.
     */
    public String getServletName() {
        return this.servletName;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.beans.factory.BeanNameAware#setBeanName(java.lang.String)
     */
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    /**
     * setInitParameters
     *
     * @param initParameters
     *            The initParameters to set.
     */
    public void setInitParameters(Properties initParameters) {
        this.initParameters = initParameters;
    }

    /**
     * setServletClass
     *
     * @param servletClass
     *            The servletClass to set.
     */
    public void setServletClass(Class servletClass) {
        this.servletClass = servletClass;
    }

    /**
     * setServletInstance
     *
     * @param servletInstance
     *            The servletInstance to set.
     */
    public void setServletInstance(Servlet servletInstance) {
        this.servletInstance = servletInstance;
    }

    /**
     * setServletName
     *
     * @param servletName
     *            The servletName to set.
     */
    public void setServletName(String servletName) {
        this.servletName = servletName;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        log.debug("handleRequestInternal()");

        servletInstance.service(request, response);

        return null;
    }

    private class DelegatingServletConfig implements ServletConfig {

        /*
         * (non-Javadoc)
         *
         * @see javax.servlet.ServletConfig#getInitParameter(java.lang.String)
         */
        public String getInitParameter(String paramName) {
            return initParameters.getProperty(paramName);
        }

        /*
         * (non-Javadoc)
         *
         * @see javax.servlet.ServletConfig#getInitParameterNames()
         */
        public Enumeration getInitParameterNames() {
            return initParameters.keys();
        }

        /*
         * (non-Javadoc)
         *
         * @see javax.servlet.ServletConfig#getServletContext()
         */
        public ServletContext getServletContext() {
            return getWebApplicationContext().getServletContext();
        }

        /*
         * (non-Javadoc)
         *
         * @see javax.servlet.ServletConfig#getServletName()
         */
        public String getServletName() {
            return servletName;
        }

    }
}
