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
package tk.okou.vertx.sdk.kotlin.my

import io.vertx.core.json.JsonObject
import io.vertx.kotlin.coroutines.awaitResult
import tk.okou.vertx.sdk.my.MyMiniGameApi

suspend fun MyMiniGameApi.code2tokenAwait(appId: String, jsCode: String, privateKey: String): JsonObject {
  return awaitResult {
    this.code2token(appId, jsCode, privateKey, it)
  }
}

suspend fun MyMiniGameApi.code2tokenAwait(appId: String, jsCode: String, grantType: String, privateKey: String): JsonObject {
  return awaitResult {
    this.code2token(appId, jsCode, grantType, privateKey, it)
  }
}

