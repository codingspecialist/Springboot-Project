package com.cos.bsymWeb.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cos.bsymWeb.repository.SharesRepository;

@Service
public class ShareService {
	@Autowired
	private SharesRepository sharesRepository;
}
