package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Controller
public class BidListController {

	private static final Logger LOG = LogManager.getLogger(BidListController.class);
	
@Autowired
private BidListService bidListService;

    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
        List<BidList> listBidList = bidListService.getAllBidList();
        model.addAttribute("listBidList", listBidList);
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
    	if (!result.hasErrors()) {
            bidListService.saveBidList(bid);
            model.addAttribute("listBidList", bidListService.getAllBidList());
            LOG.info("BidList created. Id=" + bid.getBidListId());
            return "redirect:/bidList/list";
        }
    	LOG.info("Error during BidList creation. BidList is not created");
        return "bidList/add";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	  BidList bidList = bidListService.getBidList(id).orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
          model.addAttribute("bidList", bidList);
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {
    	 if (result.hasErrors()) {
    		 LOG.info("Error during update of BidList (Id="+ id +"). Not updated");
             return "bidlist/update";
         }
    	 bidList.setBidListId(id);
    	 bidListService.saveBidList(bidList);
         model.addAttribute("listBidList", bidListService.getAllBidList());
         LOG.info("BidList (Id="+ id +") is updated");
        return "redirect:/bidList/list";
    }

    
    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        bidListService.deleteBidList(id);
        LOG.info("BidList (Id="+ id +") is deleted");
        return "redirect:/bidList/list";
    }
}
