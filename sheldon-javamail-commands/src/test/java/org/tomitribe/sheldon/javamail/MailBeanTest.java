/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tomitribe.sheldon.javamail;

import org.junit.Before;
import org.junit.Test;
import org.tomitribe.crest.Main;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class MailBeanTest {
    private Main main;

    @Before
    public void setup() {
        this.main = new Main(MailBean.class);
    }

    @Test
    public void testSend() throws Exception {
        final boolean result = (boolean) main.exec("send",
                "--smtp=server.tomitribe.com",
                "--from=mail@tomitribe.com",
                "--password=123456",
                "--to=mail2@tomitribe.com",
                "--message=aloha!");
        assertTrue(result);
    }

    @Test
    public void testListFoldersWithPop() throws Exception {
        final List<String> folders = (List<String>) main.exec("list",
                "--pop=server.tomitribe.com",
                "--from=mail@tomitribe.com",
                "--password=123456");

        assertNotNull(folders);
        assertTrue(folders.contains("inbox"));
        assertTrue(folders.contains("rock"));
        assertTrue(folders.contains("rock/nirvana"));
        assertTrue(folders.contains("rock/nirvana/nevermind"));
    }

    @Test
    public void testListFoldersWithImap() throws Exception {
        final List<String> folders = (List<String>) main.exec("list",
                "--imap=server.tomitribe.com",
                "--from=mail@tomitribe.com",
                "--password=123456");

        assertNotNull(folders);
        assertTrue(folders.contains("inbox"));
        assertTrue(folders.contains("rock"));
        assertTrue(folders.contains("rock/nirvana"));
        assertTrue(folders.contains("rock/nirvana/nevermind"));
    }

    @Test
    public void testListMailsInFolderWithPop() throws Exception {
        final List<String> folders = (List<String>) main.exec("list",
                "--pop=server.tomitribe.com",
                "--from=mail@tomitribe.com",
                "--password=123456",
                "--folder=inbox");

        assertNotNull(folders);
        assertTrue(folders.contains("{ from: norm@tomitribe.com, to: norm-father@tomitribe.com, date: 2015-10-22T11:47Z, subject: Hi, body: I love you father }"));
        assertTrue(folders.contains("{ from: norm@tomitribe.com, to: norm-mother@tomitribe.com, date: 2015-10-22T11:47Z, subject: I love you, body: I love you mother }"));
        assertTrue(folders.contains("{ from: norm@tomitribe.com, to: norm-sister@tomitribe.com, date: 2015-10-22T11:47Z, subject: Hello sister, body: I love you sister }"));
        assertTrue(folders.contains("{ from: norm@tomitribe.com, to: norm-brother@tomitribe.com, date: 2015-10-22T11:47Z, subject: Hey my brother, body: I love you brother }"));
    }

    @Test
    public void testListMailsInFolderWithImap() throws Exception {
        final List<String> folders = (List<String>) main.exec("list",
                "--imap=server.tomitribe.com",
                "--from=mail@tomitribe.com",
                "--password=123456",
                "--folder=inbox");

        assertNotNull(folders);
        assertTrue(folders.contains("{ from: norm@tomitribe.com, to: norm-father@tomitribe.com, date: 2015-10-22T11:47Z, subject: Hi, body: I love you father }"));
        assertTrue(folders.contains("{ from: norm@tomitribe.com, to: norm-mother@tomitribe.com, date: 2015-10-22T11:47Z, subject: I love you, body: I love you mother }"));
        assertTrue(folders.contains("{ from: norm@tomitribe.com, to: norm-sister@tomitribe.com, date: 2015-10-22T11:47Z, subject: Hello sister, body: I love you sister }"));
        assertTrue(folders.contains("{ from: norm@tomitribe.com, to: norm-brother@tomitribe.com, date: 2015-10-22T11:47Z, subject: Hey my brother, body: I love you brother }"));
    }

    @Test
    public void testSearchInAllOptionsWithPop() throws Exception {
        final List<String> emails = (List<String>) main.exec("search",
                "--pop=server.tomitribe.com",
                "--from=mail@tomitribe.com",
                "--password=123456",
                "--search=father");

        assertNotNull(emails);
        assertTrue(emails.contains("{ from: norm@tomitribe.com, to: norme-father@tomitribe.com, date: 2015-10-22T11:47Z, body: I love you father }"));
    }

    @Test
    public void testSearchInAllOptionsWithImap() throws Exception {
        final List<String> emails = (List<String>) main.exec("search",
                "--imap=server.tomitribe.com",
                "--from=mail@tomitribe.com",
                "--password=123456",
                "--search=father");

        assertNotNull(emails);
        assertTrue(emails.contains("{ from: norm@tomitribe.com, to: norme-father@tomitribe.com, date: 2015-10-22T11:47Z, body: I love you father }"));
    }

    @Test
    public void testSearchInSubjectWithPop() throws Exception {
        final List<String> emails = (List<String>) main.exec("search",
                "--pop=server.tomitribe.com",
                "--from=mail@tomitribe.com",
                "--password=123456",
                "--search=Hi",
                "--saerch-in=SUBJECT");

        assertNotNull(emails);
        assertTrue(emails.contains("{ from: norm@tomitribe.com, to: norm-father@tomitribe.com, date: 2015-10-22T11:47Z, subject: Hi, body: I love you father }"));
    }

    @Test
    public void testSearchInSubjectWithImap() throws Exception {
        final List<String> emails = (List<String>) main.exec("search",
                "--imap=server.tomitribe.com",
                "--from=mail@tomitribe.com",
                "--password=123456",
                "--search=Hi",
                "--saerch-in=SUBJECT");

        assertNotNull(emails);
        assertTrue(emails.contains("{ from: norm@tomitribe.com, to: norm-father@tomitribe.com, date: 2015-10-22T11:47Z, subject: Hi, body: I love you father }"));
    }

    @Test
    public void testSearchInBodyWithPop() throws Exception {
        final List<String> emails = (List<String>) main.exec("search",
                "--pop=server.tomitribe.com",
                "--from=mail@tomitribe.com",
                "--password=123456",
                "--search=love",
                "--search-in=BODY");

        assertNotNull(emails);
        assertTrue(emails.contains("{ from: norm@tomitribe.com, to: norm-father@tomitribe.com, date: 2015-10-22T11:47Z, subject: Hi, body: I love you father }"));
        assertTrue(emails.contains("{ from: norm@tomitribe.com, to: norm-mother@tomitribe.com, date: 2015-10-22T11:47Z, subject: I love you, body: I love you mother }"));
        assertTrue(emails.contains("{ from: norm@tomitribe.com, to: norm-sister@tomitribe.com, date: 2015-10-22T11:47Z, subject: Hello sister, body: I love you sister }"));
        assertTrue(emails.contains("{ from: norm@tomitribe.com, to: norm-brother@tomitribe.com, date: 2015-10-22T11:47Z, subject: Hey my brother, body: I love you brother }"));
    }

    @Test
    public void testSearchInBodyWithImap() throws Exception {
        final List<String> emails = (List<String>) main.exec("search",
                "--imap=server.tomitribe.com",
                "--from=mail@tomitribe.com",
                "--password=123456",
                "--search=love",
                "--search-in=BODY");

        assertNotNull(emails);
        assertTrue(emails.contains("{ from: norm@tomitribe.com, to: norm-father@tomitribe.com, date: 2015-10-22T11:47Z, subject: Hi, body: I love you father }"));
        assertTrue(emails.contains("{ from: norm@tomitribe.com, to: norm-mother@tomitribe.com, date: 2015-10-22T11:47Z, subject: I love you, body: I love you mother }"));
        assertTrue(emails.contains("{ from: norm@tomitribe.com, to: norm-sister@tomitribe.com, date: 2015-10-22T11:47Z, subject: Hello sister, body: I love you sister }"));
        assertTrue(emails.contains("{ from: norm@tomitribe.com, to: norm-brother@tomitribe.com, date: 2015-10-22T11:47Z, subject: Hey my brother, body: I love you brother }"));
    }

    @Test
    public void testSearchToWithPop() throws Exception {
        final List<String> emails = (List<String>) main.exec("search",
                "--pop=server.tomitribe.com",
                "--from=mail@tomitribe.com",
                "--password=123456",
                "--search=norm-sister",
                "--search-in=TO");

        assertNotNull(emails);
        assertTrue(emails.contains("{ from: norm@tomitribe.com, to: norm-sister@tomitribe.com, date: 2015-10-22T11:47Z, subject: Hello sister, body: I love you sister }"));
    }

    @Test
    public void testSearchToWithImap() throws Exception {
        final List<String> emails = (List<String>) main.exec("search",
                "--imap=server.tomitribe.com",
                "--from=mail@tomitribe.com",
                "--password=123456",
                "--search=norm-sister",
                "--search-in=TO");

        assertNotNull(emails);
        assertTrue(emails.contains("{ from: norm@tomitribe.com, to: norm-sister@tomitribe.com, date: 2015-10-22T11:47Z, subject: Hello sister, body: I love you sister }"));
    }

    @Test
    public void testSearchFromWithPop() throws Exception {
        final List<String> emails = (List<String>) main.exec("search",
                "--pop=server.tomitribe.com",
                "--from=mail@tomitribe.com",
                "--password=123456",
                "--search=norm",
                "--search-in=FROM");

        assertNotNull(emails);
        assertTrue(emails.contains("{ from: norm@tomitribe.com, to: norm-father@tomitribe.com, date: 2015-10-22T11:47Z, subject: Hi, body: I love you father }"));
        assertTrue(emails.contains("{ from: norm@tomitribe.com, to: norm-mother@tomitribe.com, date: 2015-10-22T11:47Z, subject: I love you, body: I love you mother }"));
        assertTrue(emails.contains("{ from: norm@tomitribe.com, to: norm-sister@tomitribe.com, date: 2015-10-22T11:47Z, subject: Hello sister, body: I love you sister }"));
        assertTrue(emails.contains("{ from: norm@tomitribe.com, to: norm-brother@tomitribe.com, date: 2015-10-22T11:47Z, subject: Hey my brother, body: I love you brother }"));
    }

    @Test
    public void testSearchFromWithImap() throws Exception {
        final List<String> emails = (List<String>) main.exec("search",
                "--imap=server.tomitribe.com",
                "--from=mail@tomitribe.com",
                "--password=123456",
                "--search=norm",
                "--search-in=FROM");

        assertNotNull(emails);
        assertTrue(emails.contains("{ from: norm@tomitribe.com, to: norm-father@tomitribe.com, date: 2015-10-22T11:47Z, subject: Hi, body: I love you father }"));
        assertTrue(emails.contains("{ from: norm@tomitribe.com, to: norm-mother@tomitribe.com, date: 2015-10-22T11:47Z, subject: I love you, body: I love you mother }"));
        assertTrue(emails.contains("{ from: norm@tomitribe.com, to: norm-sister@tomitribe.com, date: 2015-10-22T11:47Z, subject: Hello sister, body: I love you sister }"));
        assertTrue(emails.contains("{ from: norm@tomitribe.com, to: norm-brother@tomitribe.com, date: 2015-10-22T11:47Z, subject: Hey my brother, body: I love you brother }"));
    }
}
