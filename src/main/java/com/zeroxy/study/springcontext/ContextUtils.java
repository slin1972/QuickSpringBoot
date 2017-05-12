package com.zeroxy.study.springcontext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class ContextUtils {
 
private static ApplicationContext applicationContext;
 
	private final static Logger LOG = LoggerFactory.getLogger(ApplicationContext.class);  
		
	public static void setApplicationContext(ApplicationContext applicationContext) {
		synchronized (ContextUtils.class) {
			LOG.debug("setApplicationContext, notifyAll");
			ContextUtils.applicationContext = applicationContext;
			ContextUtils.class.notifyAll();
		}
	}
	 
	public static ApplicationContext getApplicationContext() {
		synchronized (ContextUtils.class) {
			while (applicationContext == null) {
				try {
					LOG.debug("getApplicationContext, wait...");
					ContextUtils.class.wait(60000);
					if (applicationContext == null) {
						LOG.warn("Have been waiting for ApplicationContext to be set for 1 minute", new Exception());
					}
				} catch (InterruptedException ex) {
					LOG.debug("getApplicationContext, wait interrupted");
				}
			}
			return applicationContext;
		}
	}
	
	public static <T> T getBean(Class<T> class1) {
		return getApplicationContext().getBean(class1);
	}
	public static <T> Map<String, T> getBeansOfType(Class<T> class1) {
		return getApplicationContext().getBeansOfType(class1);
	}
	public static Object getBean(String name) {
		return getApplicationContext().getBean(name);
	}

	
	/**
	 * 根据通配符资源路径获取资源列表。
	 * 
	 * @param wildcardResourcePaths
	 *            通配符资源路径
	 * @return 返回资源列表。
	 */
	public static List<Resource> getResourcesByWildcard(
			String... wildcardResourcePaths) {
		List<Resource> resources = new ArrayList<Resource>();
		try {
			ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
			for (String basename : wildcardResourcePaths) {
				for (Resource resource : resourcePatternResolver
						.getResources(basename)) {
					resources.add(resource);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("获取资源列表时反生异常。", e);
		}
		return resources;
	}

	/**
	 * 根据通配符资源路径获取资源路径列表。
	 * 
	 * @param resourceDir
	 *            资源目录
	 * @param wildcardResourcePaths
	 *            通配符资源路径
	 * @return 返回实际匹配的资源路径列表。
	 */
	public static List<String> getResourcePathsByWildcard(String resourceDir,
			String... wildcardResourcePaths) {
		List<String> resourcePaths = new ArrayList<String>();
		try {
			for (Resource resource : getResourcesByWildcard(wildcardResourcePaths)) {
				String uri = resource.getURI().toString();
				if (resource instanceof FileSystemResource
						|| resource instanceof UrlResource) {
					String resourcePath = "classpath:" + resourceDir
							+ StringUtils.substringAfter(uri, resourceDir);
					resourcePaths.add(resourcePath);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("获取资源文件时发生异常。", e);
		}
		return resourcePaths;
	}

	/**
	 * 根据通配符资源路径获取资源基本名称列表。
	 * 
	 * @param resourceDir
	 *            资源目录
	 * @param wildcardResourcePaths
	 *            通配符资源路径
	 * @return 返回实际匹配的资源基本名称列表。
	 */
	public static List<String> getResourceBasenamesByWildcard(
			String resourceDir, String... wildcardResourcePaths) {
		List<String> resourceBasenames = new ArrayList<String>();
		for (String resourcePath : getResourcePathsByWildcard(resourceDir,
				wildcardResourcePaths)) {
			resourceBasenames.add(StringUtils.substringBeforeLast(resourcePath,
					"."));
		}
		return resourceBasenames;
	}
}