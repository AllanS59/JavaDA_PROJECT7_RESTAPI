package com.nnk.springboot.domain;

import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "Bidlist")
public class BidList {

	 @Id
	 @GeneratedValue(strategy= GenerationType.AUTO)
	 private Integer BidListId;
	 @NotBlank(message = "Account is mandatory")
	 private String account;
	 @NotBlank(message = "Type is mandatory")
	 private String type;
	 private double bidQuantity;
	 private double askQuantity;
	 private double bid;
	 private double ask;
	 private String benchmark;
	 private Timestamp bidListDate;
	 private String commentary;
	 private String security;
	 private String status;
	 private String trader;
	 private String book;
	 private String creationName;
	 private Timestamp creationDate;
	 private String revisionName;
	 private Timestamp revisionDate;
	 private String dealName;
	 private String dealType;
	 private String sourceListId;
	 private String side;
	 
	 
	 public BidList() {
		}
	 
	 public BidList(String account, String type, double bidQuantity) {
		this.account = account;
		this.type = type;
		this.bidQuantity = bidQuantity;
	}
	
	public Integer getBidListId() {
		return BidListId;
	}
	public void setBidListId(Integer bidListId) {
		BidListId = bidListId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getBidQuantity() {
		return bidQuantity;
	}
	public void setBidQuantity(double bidQuantity) {
		this.bidQuantity = bidQuantity;
	}
	public double getAskQuantity() {
		return askQuantity;
	}
	public void setAskQuantity(double askQuantity) {
		this.askQuantity = askQuantity;
	}
	public double getBid() {
		return bid;
	}
	public void setBid(double bid) {
		this.bid = bid;
	}
	public double getAsk() {
		return ask;
	}
	public void setAsk(double ask) {
		this.ask = ask;
	}
	public String getBenchmark() {
		return benchmark;
	}
	public void setBenchmark(String benchmark) {
		this.benchmark = benchmark;
	}
	public Timestamp getBidListDate() {
		return new Timestamp(bidListDate.getTime());
	}
	public void setBidListDate(Timestamp bidListDate) {
		this.bidListDate = new Timestamp(bidListDate.getTime());
	}
	public String getCommentary() {
		return commentary;
	}
	public void setCommentary(String commentary) {
		this.commentary = commentary;
	}
	public String getSecurity() {
		return security;
	}
	public void setSecurity(String security) {
		this.security = security;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTrader() {
		return trader;
	}
	public void setTrader(String trader) {
		this.trader = trader;
	}
	public String getBook() {
		return book;
	}
	public void setBook(String book) {
		this.book = book;
	}
	public String getCreationName() {
		return creationName;
	}
	public void setCreationName(String creationName) {
		this.creationName = creationName;
	}
	public Timestamp getCreationDate() {
		return new Timestamp(creationDate.getTime());
	}
	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = new Timestamp(creationDate.getTime());
	}
	public String getRevisionName() {
		return revisionName;
	}
	public void setRevisionName(String revisionName) {
		this.revisionName = revisionName;
	}
	public Timestamp getRevisionDate() {
		return new Timestamp(revisionDate.getTime());
	}
	public void setRevisionDate(Timestamp revisionDate) {
		this.revisionDate = new Timestamp(revisionDate.getTime());
	}
	public String getDealName() {
		return dealName;
	}
	public void setDealName(String dealName) {
		this.dealName = dealName;
	}
	public String getDealType() {
		return dealType;
	}
	public void setDealType(String dealType) {
		this.dealType = dealType;
	}
	public String getSourceListId() {
		return sourceListId;
	}
	public void setSourceListId(String sourceListId) {
		this.sourceListId = sourceListId;
	}
	public String getSide() {
		return side;
	}
	public void setSide(String side) {
		this.side = side;
	}
	 
	 
	 
	 
	 
	 
}
