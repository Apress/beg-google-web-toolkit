/*
 * Copyright 2002-2008 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.apress.beginninggwt.chap8.fileupload.server;

import org.apache.commons.fileupload.ProgressListener;

/**
 * @author Bram Smeets
 */
public class FileUploadListener implements ProgressListener {

    private long kiloBytes = -1;

    private long bytesRead = -1;
    private long contentLength = -1;
    private int items = -1;

    public void update(long bytesRead, long contentLength, int items) {
        long kBytes = bytesRead / 1024;
        if (kiloBytes == kBytes) {
            return;
        }
        kiloBytes = kBytes;

        this.bytesRead = bytesRead;
        this.contentLength = contentLength;
        this.items = items;
    }

    public long getBytesRead() {
        return bytesRead;
    }

    public long getContentLength() {
        return contentLength;
    }

    public int getItems() {
        return items;
    }

}
