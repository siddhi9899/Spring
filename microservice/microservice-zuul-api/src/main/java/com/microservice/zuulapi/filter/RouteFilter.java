package com.microservice.zuulapi.filter;

import com.netflix.zuul.ZuulFilter;

public class RouteFilter extends ZuulFilter {

	@Override
	public String filterType() {
		return "route";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		// TODO Auto-generated method stub
		return null;
	}

	/*@Override
	public Object run() {
		System.out.println("Using Route Filter");

		return null;
	}*/

}
