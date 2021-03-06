package com.easymorse.videos.client.presenter;

import com.easymorse.videos.client.event.BrowseVideoItemsEvent;
import com.easymorse.videos.client.event.BrowseVideoItemsEventHandler;
import com.easymorse.videos.client.event.LogOffEvent;
import com.easymorse.videos.client.event.NeedLoginEvent;
import com.easymorse.videos.client.event.PlayMideaEvent;
import com.easymorse.videos.client.event.PlayMideaEventHandler;
import com.easymorse.videos.client.event.UploadCompleteEvent;
import com.easymorse.videos.client.model.VideoItem;
import com.easymorse.videos.client.model.VideoItemPagination;
import com.easymorse.videos.client.view.VideoItemView;
import com.easymorse.videos.client.view.VideoPlayerView;
import com.easymorse.videos.client.view.VideosView;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.dom.client.PreElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitHandler;

public class VideosPresenter implements Presenter, ValueChangeHandler<String> {

	private HandlerManager handlerManager;

	private VideosView videosView;

	private HasWidgets container;

	public VideosPresenter(HandlerManager handlerManager, HasWidgets container) {
		this.handlerManager = handlerManager;
		this.container = container;
		this.videosView = new VideosView();
		bind();
	}

	private void bind() {

		Window.addResizeHandler(this.videosView);

		History.addValueChangeHandler(this);

		this.handlerManager.addHandler(BrowseVideoItemsEvent.TYPE,
				new BrowseVideoItemsEventHandler() {
					@Override
					public void onBrowseVideoItem(BrowseVideoItemsEvent event) {
						videosView.getTabPanel().getTabBar().selectTab(0);
						RequestBuilder builder = new RequestBuilder(
								RequestBuilder.GET, new StringBuilder().append(
										"../browse.json?no=").append(
										event.getPageNo()).toString());

						builder.setCallback(new RequestCallback() {

							@Override
							public void onResponseReceived(Request request,
									Response response) {
								if (response.getStatusCode() == 401) {
									handlerManager
											.fireEvent(new NeedLoginEvent());
								} else {
									videosView.getBrowseWidget().clear();

									VideoItemPagination pagination = VideoItemPagination
											.fromJson(response.getText());
									pageNo = pagination.getNo();

									// VideoItem videoItem = VideoItem
									// .fromJson("{'id':'1','title':'喜羊羊与灰太狼','content':'国产原创系列电视动画片《喜羊羊与灰太狼》，由广东原创动力文化传播有限公司出品。自2005年6月推出后，陆续在全国近50家电视台热播，在北京、上海、杭州、南京、广州、福州等城市，《喜羊羊与灰太狼》最高收视率达17.3%，大大超过了同时段播出的境外动画片。'}");
									final VideoItem videoItem = pagination
											.getResults().get(0);
									VideoItemView itemView = new VideoItemView(
											videoItem);
									itemView.getPlayButton().addClickHandler(
											new ClickHandler() {
												@Override
												public void onClick(
														ClickEvent event) {
													handlerManager
															.fireEvent(new PlayMideaEvent(
																	videoItem
																			.getId()));
												}
											});
									videosView.getBrowseWidget().add(itemView);
									HorizontalPanel pagePanel = new HorizontalPanel();

									pagePanel.setSpacing(5);
									pagePanel
											.setHorizontalAlignment(HorizontalPanel.ALIGN_RIGHT);
									pagePanel.setWidth("100%");

									HorizontalPanel innerPagePanel = new HorizontalPanel();
									innerPagePanel.setSpacing(5);
									pagePanel.add(innerPagePanel);

									if (pagination.isPrevious()) {
										Image image = new Image("before.gif");
										image
												.addClickHandler(new ClickHandler() {

													@Override
													public void onClick(
															ClickEvent event) {
														pageNo--;
														handlerManager
																.fireEvent(new BrowseVideoItemsEvent(
																		pageNo));
													}
												});
										innerPagePanel.add(image);
									}

									if (pagination.isNext()) {
										Image image = new Image("next.gif");
										image
												.addClickHandler(new ClickHandler() {

													@Override
													public void onClick(
															ClickEvent event) {
														pageNo++;
														handlerManager
																.fireEvent(new BrowseVideoItemsEvent(
																		pageNo));
													}
												});
										innerPagePanel.add(image);
									}

									videosView.getBrowseWidget().add(pagePanel);

								}
							}

							@Override
							public void onError(Request request, Throwable e) {
								Window.alert("error");
							}
						});

						try {
							builder.send();
						} catch (RequestException e1) {
							e1.printStackTrace();
						}
					}
				});

		this.handlerManager.addHandler(PlayMideaEvent.TYPE,
				new PlayMideaEventHandler() {
					@Override
					public void onPlayMidea(PlayMideaEvent event) {
						if (videosView.getTabPanel().getTabBar().getTabCount() < 4) {
							RequestBuilder requestBuilder = new RequestBuilder(
									RequestBuilder.GET, "../video.do?id="
											+ event.getId());
							requestBuilder.setCallback(new RequestCallback() {
								@Override
								public void onResponseReceived(Request request,
										Response response) {
									if (response.getText().equals("wait")) {
										final DialogBox box = new DialogBox();
										int width = 160;
										box.setWidth(width + "px");
										box.setText("操作提示");
										box.setGlassEnabled(true);
										box.setPopupPosition(videosView
												.getTabPanel()
												.getAbsoluteLeft() + 100,
												videosView.getTabPanel()
														.getAbsoluteTop() + 20);// TODO
										VerticalPanel panel = new VerticalPanel();
										box.add(panel);

										panel.add(new HTML("视频转换中，请等待...."));
										Button button = new Button("关闭");

										panel.add(button);

										button
												.addClickHandler(new ClickHandler() {

													@Override
													public void onClick(
															ClickEvent event) {
														box.hide();
													}
												});

										// 临时定位
										box.show();
									} else {
										VideoPlayerView playerView = new VideoPlayerView(
												response.getText());
										videosView.getTabPanel().insert(
												playerView, "播放", 3);
										playerView.getCloseButton()
												.addClickHandler(
														new ClickHandler() {
															@Override
															public void onClick(
																	ClickEvent event) {
																videosView
																		.getTabPanel()
																		.remove(
																				3);
																videosView
																		.getTabPanel()
																		.getTabBar()
																		.selectTab(
																				0);
															}
														});

										videosView.getTabPanel().getTabBar()
												.selectTab(3);
									}
								}

								@Override
								public void onError(Request request, Throwable e) {
									Window.alert("error!!");
								}
							});

							try {
								requestBuilder.send();
							} catch (RequestException e) {
								throw new RuntimeException(e);
							}

						} else {
							videosView.getTabPanel().getTabBar().selectTab(3);
						}
					}
				});

		this.videosView.getLogoffButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				videosView.getLogoffDialogBox().hide();
				handlerManager.fireEvent(new LogOffEvent());
			}
		});

		// DialogBox uploadDialogBox = new UploadDialogBox(videosView
		// .getUploadView().getFormPanel(),this.handlerManager);
		videosView.getUploadView().getFormPanel().addSubmitHandler(
				new SubmitHandler() {
					@Override
					public void onSubmit(SubmitEvent event) {
						new UploadDialogPresenter(handlerManager, videosView
								.getUploadView().getFormPanel(), event)
								.go(container);
					}
				});

		videosView.getUploadView().getFormPanel().addSubmitCompleteHandler(
				new SubmitCompleteHandler() {
					@Override
					public void onSubmitComplete(SubmitCompleteEvent event) {
						// Window.alert(JsonUtils.unsafeEval(""));
						// Window.alert(event.getResults());
						String result = event.getResults();
						if (result.length() > 32) {
							result = result.substring(result.length() - 6 - 32,
									result.length() - 6);
						}
//						Window.alert(result);
						handlerManager
								.fireEvent(new UploadCompleteEvent(result));
					}
				});

		videosView.getUserView().getSaveButton().addClickHandler(
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						Window.alert("尚未实现");// TODO 实现修改用户权限
					}
				});

		this.videosView.getTabPanel().getTabBar().addSelectionHandler(
				new SelectionHandler<Integer>() {

					@Override
					public void onSelection(SelectionEvent<Integer> event) {
						int selectIndex = event.getSelectedItem();
						switch (selectIndex) {
						case 0:
							History.newItem("browse", true);
							break;
						case 1:
							History.newItem("upload", false);
							break;
						case 2:
							History.newItem("user", false);
							break;
						default:
							History.newItem("browse", false);
							break;
						}
					}
				});
		this.videosView.getUploadView().getSaveButton().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						videosView.getUploadView().getFormPanel().setEncoding(
								FormPanel.ENCODING_MULTIPART);
						videosView.getUploadView().getFormPanel().setMethod(
								FormPanel.METHOD_POST);
						videosView.getUploadView().getFormPanel().setAction(
								"upload.do");

						videosView.getUploadView().getFormPanel().submit();
					}
				});
	}

	@Override
	public void go(HasWidgets container) {
		if (History.getToken().equals("")) {
			History.newItem("browse");
		} else {
			History.fireCurrentHistoryState();
		}
	}

	// public static native PreElement getPreElement(String string) /*-{
	// return eval('(' + string + ')');
	// }-*/;

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();

		if (token != null) {

			if (token.equals("upload")) {
				this.videosView.getTabPanel().selectTab(1);
			} else if (token.equals("user")) {
				this.videosView.getTabPanel().selectTab(2);
			} else if (token.equals("browse")) {
				this.videosView.getTabPanel().selectTab(0);
				getBrowseData();
			}
		}
		container.add(videosView);
	}

	private int pageNo = 1;

	private void getBrowseData() {
		handlerManager.fireEvent(new BrowseVideoItemsEvent(pageNo));
	}

}
