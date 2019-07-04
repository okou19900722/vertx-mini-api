/*
 * Copyright 2019 Red Hat, Inc.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Apache License v2.0 which accompanies this distribution.
 *
 * The Eclipse Public License is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * The Apache License v2.0 is available at
 * http://www.opensource.org/licenses/apache2.0.php
 *
 * You may elect to redistribute this code under either of these licenses.
 */
package tk.okou.vertx.sdk.tencent.kotlin.wechat

import io.vertx.core.json.JsonObject
import io.vertx.kotlin.coroutines.awaitResult
import tk.okou.vertx.sdk.tencent.wechat.WechatMiniApi

suspend fun WechatMiniApi.code2sessionAwait(appId: String, secret: String, jsCode: String): JsonObject {
  return awaitResult {
    this.code2session(appId, secret, jsCode, it)
  }
}

suspend fun WechatMiniApi.code2sessionAwait(appId: String, secret: String, jsCode: String, grantType: String): JsonObject {
  return awaitResult {
    this.code2session(appId, secret, jsCode, grantType, it)
  }
}

suspend fun WechatMiniApi.getAccessTokenAwait(appId: String, secret: String): JsonObject {
  return awaitResult {
    this.getAccessToken(appId, secret, it)
  }
}

suspend fun WechatMiniApi.getAccessTokenAwait(grantType: String, appId: String, secret: String): JsonObject {
  return awaitResult {
    this.getAccessToken(grantType, appId, secret, it)
  }
}

