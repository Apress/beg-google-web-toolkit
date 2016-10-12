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

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

/**
 * @author Bram Smeets
 */
public class FileUploadServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        FileUploadListener listener = (FileUploadListener) session.getAttribute("progress");

        if (listener != null) {
            StringBuilder msg = new StringBuilder();
            msg.append(formatSize(listener.getBytesRead()));
            msg.append("/");
            msg.append(formatSize(listener.getContentLength()));

            response.getOutputStream().write(msg.toString().getBytes());
            response.getOutputStream().close();
        }
    }

    private String formatSize(long bytes) {
        long kBytes = bytes / 1024;

        if (kBytes > 1024) {
            return kBytes / 1024 + "Mb";
        } else {
            return kBytes + "kb";
        }
    }

    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        System.out.println("System.getProperty(\"java.io.tmpdir\") = " + System.getProperty("java.io.tmpdir"));
        // Check that we have a file upload request
        if(ServletFileUpload.isMultipartContent(request)) {
            HttpSession session = request.getSession();
            System.out.println("session (post) = " + session.getId());
            FileUploadListener listener = new FileUploadListener();
            session.setAttribute("progress", listener);

            // Create a factory for disk-based file items
            DiskFileItemFactory factory = new DiskFileItemFactory();
            System.out.println("factory.getRepository() = " + factory.getRepository());

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setProgressListener(listener);

            // Parse the request
            List<FileItem> items;
            try {
                items = upload.parseRequest(request);
            } catch (FileUploadException e) {
                throw new ServletException("An error occurred while handling file upload", e);
            }

            for (FileItem item : items) {
                if (!item.isFormField()) {
                    // code here to process the file
                    System.out.println("item = " + item);
                }
            }
        }
    }

}
