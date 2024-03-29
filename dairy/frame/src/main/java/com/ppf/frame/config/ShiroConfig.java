package com.ppf.frame.config;



import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;


	@Configuration
	public class ShiroConfig {

		@Bean
		public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
			ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
			shiroFilterFactoryBean.setSecurityManager(securityManager);

			//拦截器  从上到下执行
			Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
			//不被拦截
			filterChainDefinitionMap.put("/static/**", "anon");
			//配置退出 shiro已经帮我们实现退出的代码
			filterChainDefinitionMap.put("/logout", "logout");
			//需要权限验证
			filterChainDefinitionMap.put("/**", "authc");
			//登陆页面
			shiroFilterFactoryBean.setLoginUrl("/frame/user/login");
			//登陆成功跳转页面
			shiroFilterFactoryBean.setSuccessUrl("/index");

			//未授权页面
			shiroFilterFactoryBean.setUnauthorizedUrl("/403");
			shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
			return shiroFilterFactoryBean;
		}

		/**
		 * 凭证匹配器（（由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了）
		 * @return
		 */
		@Bean
		public HashedCredentialsMatcher hashedCredentialsMatcher() {
			HashedCredentialsMatcher hashedCredentialsMather = new HashedCredentialsMatcher();
			hashedCredentialsMather.setHashAlgorithmName("md5"); //散列算法:这里使用MD5算法;
			hashedCredentialsMather.setHashIterations(1); //散列的次数，如果散列两次，相当于 md5(md5(""));
			return hashedCredentialsMather;
		}

		@Bean
		public Realm myShiroRealm() {
			Realm myShiroRealm = new Realm();
			//这个是配置shiro的密码 是否需要加密的 以及加密方式是什么的
			//myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
			return myShiroRealm;
		}

		@Bean
		public SecurityManager securityManager() {
			DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
			securityManager.setRealm(myShiroRealm());
			return securityManager;
		}

		/**
		 * 开启shiro aop注解支持
		 * @param securityManager
		 * @return
		 */
		@Bean
		public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
			AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
			authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
			return authorizationAttributeSourceAdvisor;
		}

		@Bean(name="simpleMappingExceptionResolver")
		public SimpleMappingExceptionResolver createSimpleMappingExceptionResolver() {
			SimpleMappingExceptionResolver r = new SimpleMappingExceptionResolver();
			Properties mappings = new Properties();
			mappings.setProperty("DatabaseException", "databaseError");
			mappings.setProperty("UnauthorizedException", "403");
			r.setExceptionMappings(mappings);
			r.setDefaultErrorView("error");
			r.setExceptionAttribute("ex");
			return r;
		}

}
