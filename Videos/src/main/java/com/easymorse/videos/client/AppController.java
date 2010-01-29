package com.easymorse.videos.client;

import com.easymorse.videos.client.event.LogOffEvent;
import com.easymorse.videos.client.event.LogOffEventHandler;
import com.easymorse.videos.client.event.LoginSuccessEvent;
import com.easymorse.videos.client.event.LoginSuccessEventHandler;
import com.easymorse.videos.client.event.NeedLoginEvent;
import com.easymorse.videos.client.event.NeedLoginEventHandler;
import com.easymorse.videos.client.presenter.LoginPresenter;
import com.easymorse.videos.client.presenter.Presenter;
import com.easymorse.videos.client.presenter.VideosPresenter;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;

public class AppController implements Presenter {

	private HandlerManager handlerManager;

	private HasWidgets container;

	private VideosPresenter videosPresenter;

	private String userName;

	public AppController(HandlerManager handlerManager, HasWidgets container) {
		this.handlerManager = handlerManager;
		this.container = container;
		this.bind();
		this.videosPresenter = new VideosPresenter(this.handlerManager,
				container);
	}

	@Override
	public void go(HasWidgets container) {
		if (userName == null) {
			handlerManager.fireEvent(new NeedLoginEvent());
			return;
		}
		container.clear();
		videosPresenter.go(container);
	}

	private void bind() {
		this.handlerManager.addHandler(LoginSuccessEvent.TYPE,
				new LoginSuccessEventHandler() {

					@Override
					public void onAuthenticationSuccess(LoginSuccessEvent event) {
						userName = event.getUserName();
						go(container);
					}
				});
		this.handlerManager.addHandler(LogOffEvent.TYPE,
				new LogOffEventHandler() {
					@Override
					public void onLogOff(LogOffEvent event) {
						RequestBuilder builder = new RequestBuilder(
								RequestBuilder.GET,
								"../j_spring_security_logout");
						builder.setCallback(new RequestCallback() {

							@Override
							public void onResponseReceived(Request request,
									Response response) {
								if (response.getStatusCode() < 400) {
									Window.Location.reload();
								}
							}

							@Override
							public void onError(Request request, Throwable e) {
								Window.alert("error");
							}
						});

						try {
							builder.send();
						} catch (RequestException e) {
							e.printStackTrace();
						}
					}
				});
		this.handlerManager.addHandler(NeedLoginEvent.TYPE,
				new NeedLoginEventHandler() {

					@Override
					public void onNeedLogin(NeedLoginEvent event) {
						new LoginPresenter(handlerManager).go(container);
					}
				});

	}
}
