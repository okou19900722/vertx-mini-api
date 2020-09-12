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
package tk.okou.vertx.sdk.kotlin

import io.vertx.core.json.JsonObject
import io.vertx.kotlin.coroutines.awaitResult
import tk.okou.vertx.sdk.BaseMiniProgramApi

@Deprecated(message = "Instead use code2session returning a future and chain with await()", replaceWith = ReplaceWith("code2session(appId, secret, jsCode).await()"))
suspend fun BaseMiniProgramApi.code2sessionAwait(appId: String, secret: String, jsCode: String): JsonObject {
  return awaitResult {
    this.code2session(appId, secret, jsCode, it)
  }
}

@Deprecated(message = "Instead use code2session returning a future and chain with await()", replaceWith = ReplaceWith("code2session(appId, secret, jsCode, grantType).await()"))
suspend fun BaseMiniProgramApi.code2sessionAwait(appId: String, secret: String, jsCode: String, grantType: String): JsonObject {
  return awaitResult {
    this.code2session(appId, secret, jsCode, grantType, it)
  }
}

@Deprecated(message = "Instead use getAccessToken returning a future and chain with await()", replaceWith = ReplaceWith("getAccessToken(appId, secret).await()"))
suspend fun BaseMiniProgramApi.getAccessTokenAwait(appId: String, secret: String): JsonObject {
  return awaitResult {
    this.getAccessToken(appId, secret, it)
  }
}

@Deprecated(message = "Instead use getAccessToken returning a future and chain with await()", replaceWith = ReplaceWith("getAccessToken(grantType, appId, secret).await()"))
suspend fun BaseMiniProgramApi.getAccessTokenAwait(grantType: String, appId: String, secret: String): JsonObject {
  return awaitResult {
    this.getAccessToken(grantType, appId, secret, it)
  }
}

