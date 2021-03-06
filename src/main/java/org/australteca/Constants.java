/*
 * $Header: /cvsroot/securityfilter/securityfilter/src/example/org/securityfilter/example/Constants.java,v 1.5 2004/01/26 10:55:37 maxcooper Exp $
 * $Revision: 1.5 $
 * $Date: 2004/01/26 10:55:37 $
 *
 * ====================================================================
 * The SecurityFilter Software License, Version 1.1
 *
 * (this license is derived and fully compatible with the Apache Software
 * License - see http://www.apache.org/LICENSE.txt)
 *
 * Copyright (c) 2002 SecurityFilter.org. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by
 *        SecurityFilter.org (http://www.securityfilter.org/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The name "SecurityFilter" must not be used to endorse or promote
 *    products derived from this software without prior written permission.
 *    For written permission, please contact license@securityfilter.org .
 *
 * 5. Products derived from this software may not be called "SecurityFilter",
 *    nor may "SecurityFilter" appear in their name, without prior written
 *    permission of SecurityFilter.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE SECURITY FILTER PROJECT OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 */

package org.australteca;

/**
 * Constants - constants for the example applications to facilitate testing
 *
 * @author Max Cooper (max@maxcooper.com)
 * @version $Revision: 1.5 $ $Date: 2004/01/26 10:55:37 $
 */
public interface Constants {

    /* ~~~~~~~~~~~ SecurityFilter Login Constants ~~~~~~~~~~~~~~~~~~~~ */

    String LOGIN_FORM_ID = "loginForm";
    String LOGIN_FORM_ACTION = "j_security_check";
    String LOGIN_USERNAME_FIELD = "j_username";
    String LOGIN_PASSWORD_FIELD = "j_password";
    String LOGIN_REMEMBERME_FIELD = "j_rememberme";

    /* ~~~~~~~~~~~ Servlet Constants ~~~~~~~~~~~~~~~~ */

    String COMMAND_TYPE = "commandType";

    /* ~~~~~~~~~~~ User Constants ~~~~~~~~~~~ */

    String ADMINISTRATOR = "admin";
    String MODERATOR = "moderator";
    String STANDARD = "user";

    /* ~~~~~~~~~~~ Servlet Feedback Constants ~~~~~~~~~~~~~~~~~ */

    String STATUS = "status";
    String CALLER = "caller";

    /* ~~~~~~~~~~~ JSP View Constants ~~~~~~~~~~~~ */

    String MY_HOME_TITLE = "Australteca";

    /* ~~~~~~~~~~~ Register Constants ~~~~~~~~~~~~ */
    String REGISTER_FORM_ID = "registerForm";
    String REGISTER_FORM_ACTION = "registerConfirmation";

    /* ~~~~~~~~~~~ User Servlet Parameters ~~~~~~~~~~~~~~~*/

    String NAME_PARAM = "name";
    String LAST_NAME_PARAM = "lastname";
    String EMAIL_PARAM = "email";
    String PASSWORD_PARAM = "password";
    String PASSWORD_CONFIRMATION_PARAM = "passwordC";
    String INGENIERIA_INF_VALUE = "Ingenieria informatica";
    String INGENIERIA_IND_VALUE = "Ingenieria industrial";
    String CAREER_PARAM = "career";
    String USER_ID_PARAM = "id";
    String ROLE_PARAM = "role";
    String USER_COMMENTARY_LIST = "userCommentaryList";
    String USER_SUBJECT_LIST = "userSubjectList";
    String USER_DISCUSSION_LIST = "userDiscussionList";
    String USER_FAVORITE_PUBLICATIONS_LIST = "userFavoritePublicationList";
    String USER_PUBLICATION_LIST = "userPublicationList";
    String AMOUNT_OF_UPLOADED_NOTES = "amountOfUploadedNotes";
    String AMOUNT_OF_MODERATED_SUBJECTS = "amountOfModeratedSubjects";
    String AMOUNT_OF_COMMENTARIES = "amountOfCommentaries";

    String USER_EMAIL_PARAM = "userEmail";
    /*~~~~~~~~~~~~~ User - Subject Servlet Parameters ~~~~~~~~~~ */
    String FAVORITE_PARAM = "favorite";

    String MAKE_FAVORITE = "makeFavorite";

    String REMOVE_FAVORITE = "removeFavorite";

    /* ~~~~~~~~~~~~ Subject - Commentary Servlet Parameters ~~~~~~~ */
    String COMMENTARY = "commentaryText";

    String COMMENTARY_ID = "commentaryID";

    /* ~~~~~~~~~~~~ Email Servlet Parameters ~~~~~~~~~~~~~~~~ */

    String EMAIL_DESCRIPTION = "emailDescription";
    String EMAIL_TOPIC = "emailTopic";

    /* ~~~~~~~~~~~~ Subject_Moderator Relationship Servlet Parameters ~~~~~~~~ */
    String ACCEPTED_LIST = "acceptedList";

    String WAITING_LIST = "waitingList";

    String LIST_TYPE_PARAM = "listType";
    /* ~~~~~~~~~~~ Professor - Servlet Parameters ~~~~~~~~~~~ */
    String PROFESSOR_NAME_PARAM = "professorName";
    String PROFESSOR_LAST_NAME_PARAM = "professorLastName";
    String PROFESSOR_EMAIL_PARAM = "professorEmail";

    String PROFESSOR_INFORMATION_PARAM = "professorInformation";

    String PROFESSOR_ID_PARAM = "professorID";
    /* ~~~~~~~~~~~ Subject Servlet Parameters ~~~~~~~~~~~~~ */
    String SUBJECT_NAME_PARAM = "subjectName";
    String OPERATION_SUCCESFUL_PARAM = "operationSuccessful";
    String SUBJECT_COMMENTARY_LIST = "subjectCommentaryList";
    String SUBJECT_PROFESSOR_WRAPPER_LIST = "subjectProfessorList";
    String SUBJECT_NOTES_LIST = "subjectFilesList";
    String SUBJECT_SCORE = "subjectScore";
    String SUBJECT_SCORE_PARAM = "subjectScoreParam";

    String SUBJECT_USER_SCORE = "subjectUserScore";

    String WANNABE_MODERATOR_PARAM = "wannabeModeratorParam";
    String MODERATOR_PARAM = "moderatorParam";
    /* ~~~~~~~~~~~ Upload - Download Servlet Parameters ~~~~~~~~~~~~~~~ */
    String NOTE_NAME_PARAM = "noteName";

    String NOTE_TYPE_PARAM = "noteType";

    String NOTE_ID_PARAM = "noteID";
    String NOTE_FORMAT_PARAM = "noteFormat";
    /* ~~~~~~~~~~~ Note Types ~~~~~~~~~~~~~~~~~~~ */
    String NOTE_TYPE_TEORIA = "Teoria";
    String NOTE_TYPE_GUIA = "Guía";
    String NOTE_TYPE_GUIA_RESUELTA = "Guía Resuelta";

    String NOTE_TYPE_PARCIAL = "Parcial";

    String NOTE_TYPE_FINAL = "Final";

    String NOTE_TYPE_RESUMEN = "Resumen";

    /* ~~~~~~~~~~~~~~~~ Publication Types ~~~~~~~~~~~~~~~~~ */
    String WORK_PUBLICATION = "Trabajo";
    String INVESTIGATION_PUBLICATION = "Investigacion";

    /* ~~~~~~~~~~~~~~~~ Publication Servlet Parameters ~~~~~~~~ */

    String PUBLICATION_NAME = "publicationName";
    String PUBLICATION_REQUIREMENTS = "publicationRequirements";
    String PUBLICATION_DESCRIPTION  = "publicationDescription";
    String PUBLICATION_ROLE = "publicationRole";
    String PUBLICATION_ID = "publicationID";
    String INVESTIGATION_PUBLICATION_LIST = "investigationList";
    String WORK_PUBLICATION_LIST = "workList";
    String HTML_MARKDOWN_DESCRIPTIONS = "htmlMarkdownDescription";

    String WORK_PAGE_NUMBER = "workPageNumber";
    String INVESTIGATION_PAGE_NUMBER = "investigationPageNumber";
    String WORK_TOTAL_PAGES = "workTotalPages";
    String INVESTIGATION_TOTAL_PAGES = "investigationTotalPages";

    /* ~~~~~~~~~~~ Admin Username and Password ~~~~~~~~~~~~~~~*/
    String ADMIN_USERNAME = "admin@australteca";
    String ADMIN_PASSWORD = "admin";
    String ADMIN_USERNAME_1 = "tomas.martinez@ing.austral.edu.ar";

    String ADMIN_USERNAME_2 = "tomas.forman@ing.austral.edu.ar";

    String PROFESSOR_LIST = "professorList";

    /* ~~~~~~~~~~~ Authentification Type ~~~~~~~~~~~~~~~~~ */
    String GOOGLE_OAUTH = "googleOAuth";
    String FACEBOOK_OAUTH = "facebookOAuth";
    String OAUTH_TYPE = "OAuthType";
    String SEARCH_PATTERN = "searchField";
}
