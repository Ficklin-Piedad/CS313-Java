/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.byui.myblogproject.Blog;


/**
 *
 * @author piedad
 */

public class post {
    String user;
    String dateAndTime;
    String comment;
    
    post(String user, String dateTime, String comment) {
        this.user = user;
        this.dateAndTime = dateTime;
        this.comment = comment;
    }
}

   