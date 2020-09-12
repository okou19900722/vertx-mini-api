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
package tk.okou.vertx.sdk.kotlin.tencent.qq

import io.vertx.core.json.JsonObject
import io.vertx.kotlin.coroutines.awaitResult
import tk.okou.sdk.util.SignatureMethod
import tk.okou.vertx.sdk.model.KVData
import tk.okou.vertx.sdk.tencent.qq.QQMiniGameApi

@Deprecated(message = "Instead use code2session returning a future and chain with await()", replaceWith = ReplaceWith("code2session(appId, secret, jsCode).await()"))
suspend fun QQMiniGameApi.code2sessionAwait(appId: String, secret: String, jsCode: String): JsonObject {
  return awaitResult {
    this.code2session(appId, secret, jsCode, it)
  }
}

@Deprecated(message = "Instead use code2session returning a future and chain with await()", replaceWith = ReplaceWith("code2session(appId, secret, jsCode, grantType).await()"))
suspend fun QQMiniGameApi.code2sessionAwait(appId: String, secret: String, jsCode: String, grantType: String): JsonObject {
  return awaitResult {
    this.code2session(appId, secret, jsCode, grantType, it)
  }
}

@Deprecated(message = "Instead use getAccessToken returning a future and chain with await()", replaceWith = ReplaceWith("getAccessToken(appId, secret).await()"))
suspend fun QQMiniGameApi.getAccessTokenAwait(appId: String, secret: String): JsonObject {
  return awaitResult {
    this.getAccessToken(appId, secret, it)
  }
}

@Deprecated(message = "Instead use getAccessToken returning a future and chain with await()", replaceWith = ReplaceWith("getAccessToken(grantType, appId, secret).await()"))
suspend fun QQMiniGameApi.getAccessTokenAwait(grantType: String, appId: String, secret: String): JsonObject {
  return awaitResult {
    this.getAccessToken(grantType, appId, secret, it)
  }
}

@Deprecated(message = "Instead use setUserStorage returning a future and chain with await()", replaceWith = ReplaceWith("setUserStorage(accessToken, openId, sessionKey, kvList).await()"))
suspend fun QQMiniGameApi.setUserStorageAwait(accessToken: String, openId: String, sessionKey: String, kvList: List<KVData>): JsonObject {
  return awaitResult {
    this.setUserStorage(accessToken, openId, sessionKey, kvList, it)
  }
}

@Deprecated(message = "Instead use setUserStorage returning a future and chain with await()", replaceWith = ReplaceWith("setUserStorage(accessToken, openId, sessionKey, signatureMethod, kvList).await()"))
suspend fun QQMiniGameApi.setUserStorageAwait(accessToken: String, openId: String, sessionKey: String, signatureMethod: SignatureMethod, kvList: List<KVData>): JsonObject {
  return awaitResult {
    this.setUserStorage(accessToken, openId, sessionKey, signatureMethod, kvList, it)
  }
}

@Deprecated(message = "Instead use removeUserStorage returning a future and chain with await()", replaceWith = ReplaceWith("removeUserStorage(accessToken, openId, sessionKey, keys).await()"))
suspend fun QQMiniGameApi.removeUserStorageAwait(accessToken: String, openId: String, sessionKey: String, keys: List<String>): JsonObject {
  return awaitResult {
    this.removeUserStorage(accessToken, openId, sessionKey, keys, it)
  }
}

@Deprecated(message = "Instead use removeUserStorage returning a future and chain with await()", replaceWith = ReplaceWith("removeUserStorage(accessToken, openId, sessionKey, signatureMethod, key).await()"))
suspend fun QQMiniGameApi.removeUserStorageAwait(accessToken: String, openId: String, sessionKey: String, signatureMethod: SignatureMethod, key: List<String>): JsonObject {
  return awaitResult {
    this.removeUserStorage(accessToken, openId, sessionKey, signatureMethod, key, it)
  }
}

