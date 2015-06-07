/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.personal_organizer;

import com.personal_organizer.view.OFrame;
import static javax.swing.WindowConstants.HIDE_ON_CLOSE;

/**
 *
 * @author Tolik
 */
public class About extends OFrame{
    public About()
    {
        this.setTitle("About");
        this.setSize(200, 200);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
    }
}
