package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;

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
public class TradeController {
	
	private static final Logger LOG = LogManager.getLogger(TradeController.class);
	
   @Autowired
   private TradeService tradeService;
   

    @RequestMapping("/trade/list")
    public String home(Model model)
    {
    	 List<Trade> listTrade = tradeService.getAllTrade();
         model.addAttribute("listTrade", listTrade);
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(Trade bid) {
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
    	if (!result.hasErrors()) {
            tradeService.saveTrade(trade);
            model.addAttribute("listTrade", tradeService.getAllTrade());
            LOG.info("Trade created. Id=" + trade.getTradeId());
            return "redirect:/trade/list";
        }
    	LOG.info("Error during Trade creation. Trade is not created");
        return "trade/add";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	Trade trade = tradeService.getTrade(id).orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
        model.addAttribute("trade", trade);
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
    	 if (result.hasErrors()) {
    		 LOG.info("Error during update of Trade (Id="+ id +"). Not updated");
             return "trade/update";
         }
    	 trade.setTradeId(id);
    	 tradeService.saveTrade(trade);
         model.addAttribute("listTrade", tradeService.getAllTrade());
         LOG.info("Trade (Id="+ id +") is updated");
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
       tradeService.deleteTrade(id);
       LOG.info("Trade (Id="+ id +") is deleted");
        return "redirect:/trade/list";
    }
}
