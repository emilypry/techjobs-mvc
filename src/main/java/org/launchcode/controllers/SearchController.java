package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {


    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }


    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "results")
    public String results(Model model, @RequestParam String searchType, @RequestParam String searchTerm){
        //Gets searchType and searchTerm from search.html
        ArrayList<HashMap<String, String>> results = new ArrayList<HashMap<String, String>>();
        int numOfResults = 0;

        if(searchType.equals("all")){   // If they click all
            results = JobData.findByValue(searchTerm);

        } else {    // If they click one of the categories
            results = JobData.findByColumnAndValue(searchType, searchTerm);
        }
        numOfResults = results.size();


        model.addAttribute("items", results);
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("numOfResults", numOfResults);
        return "search";

        //NOT WORKING; GET ERROR WHEN SUBMIT

    }

}
