package com.hcl.banking.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionDetails {

	private Long debitAccountNum;
	private Long creditAccountNum;
	private Double amount;

}
