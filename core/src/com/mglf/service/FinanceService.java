package com.mglf.service;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mglf.base.AppException;
import com.mglf.dao.interceptor.DbInterceptor;
import com.mglf.dto.JsonResult;
import com.mglf.finance.dto.OptRecordDto;
import com.mglf.finance.dto.PayAccountDto;
import com.mglf.finance.dto.book.BookConditionDto;
import com.mglf.finance.dto.book.BookDto;
import com.mglf.finance.dto.book.BookRecordDto;
import com.mglf.finance.dto.book.EntBookConditionDto;
import com.mglf.finance.dto.book.EntReceivablesBookConditionDto;
import com.mglf.finance.dto.book.PerBookConditionDto;
import com.mglf.finance.dto.book.SysBookConditionDto;
import com.mglf.finance.dto.invoice.InvoiceConditionDto;
import com.mglf.finance.dto.invoice.InvoiceRecordDto;
import com.mglf.finance.dto.withdrawals.PerWithdrawalsConditionDto;
import com.mglf.finance.dto.withdrawals.WithdrawalsConditionDto;
import com.mglf.finance.dto.withdrawals.WithdrawalsRequestDto;
import com.mglf.finance.util.SignUtil;
import com.mglf.util.ConfigUtil;
import com.mglf.util.JsonUtil;


/**
 * finance service
 * 
 * @author zhongzhuohan
 * 
 * Example:
 * 一。查询
 * 1）查询系统余额
 * 		BookDto bookDto = financeService.searchSysBookSummary();
 *		double balance = bookDto.getBalance().doubleValue;
 * 2）查询企业余额
 * 		BookDto bookDto = financeService.searchEntBookSummary("entId");
 *		double balance = bookDto.getBalance().doubleValue;
 * 3）查询个人余额
 * 		BookDto bookDto = financeService.searchPerBookSummary("perUserId");
 *		double balance = bookDto.getBalance().doubleValue;
 * 4）查询企业应收账款
 * 		EntReceivablesBookConditionDto entReceivablesBookConditionDto = new EntReceivablesBookConditionDto();
 *		entReceivablesBookConditionDto.setRefData("offerid:123456");
 *		List<BookRecordDto> bookRecordList = financeService.searchBookDetailList(entReceivablesBookConditionDto);
 * 二。记账
 * 1）添加企业应收账款
 *		List<BookRecordDto> bookRecordList = new ArrayList<BookRecordDto>();
 *		EntReceivablesBookRecordDto entReceivablesBookRecordDto = new EntReceivablesBookRecordDto();
 *		entReceivablesBookRecordDto.setAmount(new BigDecimal(receivablesAmount).multiply(new BigDecimal(-1)));
 *		entReceivablesBookRecordDto.setEntId("ent:12345");
 *		bookRecordList.add(entReceivablesBookRecordDto);
 *		SysBookRecordDto sysBookRecord = new SysBookRecordDto();
 *		sysBookRecord.setAmount(new BigDecimal(receivablesAmount));
 *		bookRecordList.add(sysBookRecord);
		
 *		OptRecordDto optRecord = new OptRecordDto();
 *		optRecord.setOptType(OptRecordDto.OPT_TYPE_ENTREC2SYS);
 *		optRecord.setOptUserType(OptRecordDto.OPT_USER_TYPE_ADMIN);
 *		optRecord.setOptUserName("admin_user_test");
 *		optRecord.setOptUserId("adminuserid:12345");
 *		optRecord.setDescription("应收款");
 *		optRecord.setRefData("offerid:123456");
 *		
 *		String batciId = financeService.account(bookRecordList, optRecord);
 *
 * 2）收到企业应收款
 * 		bookRecordList = new ArrayList<BookRecordDto>();
 *		entReceivablesBookRecordDto = new EntReceivablesBookRecordDto();
 *		entReceivablesBookRecordDto.setAmount(new BigDecimal(receivablesAmount));
 *		entReceivablesBookRecordDto.setEntId("ent:12345");
 *		bookRecordList.add(entReceivablesBookRecordDto);
 *		BankBookRecordDto bankBookRecord = new BankBookRecordDto();
 *		bankBookRecord.setPayAccountCode(BankBookRecordDto.PAY_ACCOUNT_CODE_CMB_OFFLINE);
 *		bankBookRecord.setAmount(new BigDecimal(receivablesAmount).multiply(new BigDecimal(-1)));
 *		bookRecordList.add(bankBookRecord);
 *		
 *		optRecord = new OptRecordDto();
 *		optRecord.setOptType(OptRecordDto.OPT_TYPE_BANK2ENTREC);
 *		optRecord.setOptUserType(OptRecordDto.OPT_USER_TYPE_ADMIN);
 *		optRecord.setOptUserName("admin_user_test");
 *		optRecord.setOptUserId("adminuserid:12345");
 *		optRecord.setDescription("应收款收到");
 *		optRecord.setRefData("offerid:123456");
 *
 * 3）企业消费
 *		List<BookRecordDto> bookRecordList = new ArrayList<BookRecordDto>();
 *		EntBookRecordDto entBookRecord = new EntBookRecordDto();
 *		entBookRecord.setAmount(new BigDecimal(consumeAmount).multiply(new BigDecimal(-1)));
 *		entBookRecord.setEntId("ent:12345");
 *		bookRecordList.add(entBookRecord);
 *		SysBookRecordDto sysBookRecord = new SysBookRecordDto();
 *		sysBookRecord.setAmount(new BigDecimal(consumeAmount));
 *		bookRecordList.add(sysBookRecord);
 *		
 *		OptRecordDto optRecord = new OptRecordDto();
 *		optRecord.setOptType(OptRecordDto.OPT_TYPE_ENT2SYS);
 *		optRecord.setOptUserType(OptRecordDto.OPT_USER_TYPE_ENT);
 *		optRecord.setOptUserName("ent_user_test");
 *		optRecord.setOptUserId("entuserid:12345");
 *		optRecord.setDescription("消费");
 *		
 *		String form = financeService.account(bookRecordList, optRecord);
 * 
 * 三。发票
 * 1）企业开票
 *		EntInvoiceRecordDto invoiceRecord = new EntInvoiceRecordDto();
 *		invoiceRecord.setInvoiceType(EntInvoiceRecordDto.INVOICE_TYPE_OFFER);
 *		invoiceRecord.setInvoiceAmount(new BigDecimal(100));
 *		invoiceRecord.setInvoiceNumber("123");
 *		invoiceRecord.setInvoiceDatetime(new Date());
 *		invoiceRecord.setEntId("ent123");
 *		invoiceRecord.setEntName("test ent");
 *		invoiceRecord.setOptUserType(OptRecordDto.OPT_USER_TYPE_ENT);
 *		invoiceRecord.setOptUserName("ent_user_test");
 *		invoiceRecord.setOptUserId("entuserid:12345");
 *		invoiceRecord.setDescription("offer");
 *		invoiceRecord.setRefData("offerid:123456");
 *		String ret = financeService.invoice(invoiceRecord);
 *
 * 四。支付接口
 * 1）获取支付接口列表
 *		List<PayAccountDto> list1 = financeService.getPayAccountList(PayAccountDto.TYPE_GATHERING);
 *		view.addObject("payAccountList1", list1);
 * 2）创建支付HTML表单
 *		List<BookRecordDto> targetBookRecordList = new ArrayList<BookRecordDto>();
 *		EntBookRecordDto bookRecord = new EntBookRecordDto();
 *		bookRecord.setAmount(new BigDecimal(payAmount));
 *		bookRecord.setEntId("ent:12345");
 *		targetBookRecordList.add(bookRecord);
 *		
 *		OptRecordDto optRecord = new OptRecordDto();
 *		optRecord.setOptUserType(OptRecordDto.OPT_USER_TYPE_ENT);
 *		optRecord.setOptUserName("ent_user_test");
 *		optRecord.setOptUserId("entuserid:12345");
 *		optRecord.setDescription("充值："+payAccountCode);
 *		
 *		String form = financeService.createPayForm(payAccountCode, targetBookRecordList, optRecord);
 *
 * 五。提现请求
 * 1）个人提现请求
 * 		PerWithdrawalsRequestDto withdrawalsRequest = new PerWithdrawalsRequestDto();
 * 		withdrawalsRequest.setAmount(new BigDecimal(100));
 * 		withdrawalsRequest.setPerName("test");
 * 		withdrawalsRequest.setPerUserId("123");
 * 		String ret = financeService.reqWithdrawals(withdrawalsRequest);
 * 		
 * 2）个人提现请求查询		
 * 		PerWithdrawalsConditionDto condition = new PerWithdrawalsConditionDto();
 * 		List<WithdrawalsRequestDto> list = financeService.searchWithdrawalsReqList(condition);
 * 
 */
@Service
@Transactional
public class FinanceService {

	private String getFinServerUrl(String path) {
		String url = ConfigUtil.readSysValue("FINANCE.SERVER.URL");
		return url + path;
	}


	private JSONObject doRequest(String path, NameValuePair[] nvps)
			throws Exception {
		nvps = SignUtil.sign(nvps);

		String url = getFinServerUrl(path);

		HttpClient client = new HttpClient();
		client.getHttpConnectionManager().getParams().setConnectionTimeout(3000);
		client.getHttpConnectionManager().getParams().setSoTimeout(3000);
		PostMethod postMethod = new PostMethod(url);

		postMethod.getParams().setParameter(
				HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");

		postMethod.setRequestBody(nvps);
		int statusCode = client.executeMethod(postMethod);
		String resp = "";

		if (statusCode == HttpStatus.SC_OK) {
			resp = postMethod.getResponseBodyAsString();
		} else {
			postMethod.releaseConnection();
			throw new AppException("连接财务服务器失败！");
		}

		postMethod.releaseConnection();

		JSONObject json = JSONObject.fromObject(resp);
		if (!JsonResult.SUCCESS.equals(json.get("status"))) {
			throw new AppException(path + " 出错：" + json.getString("msg"));
		}

		return json;
	}

	
	
	
	
	/**
	 * 查询支付账户列表
	 * 
	 * @param payType
	 *            支付类型
	 */
	public List<PayAccountDto> getPayAccountList(int payType) throws Exception {
		NameValuePair[] nvps = {new NameValuePair("payType", String.valueOf(payType))};
		JSONObject json = doRequest("/finance/getPayAccountList", nvps);

		JSONArray array = json.getJSONArray("result");
		List<PayAccountDto> list = new ArrayList<PayAccountDto>(array.size());
		for (int i = 0; i < array.size(); i++) {
			PayAccountDto item = (PayAccountDto) JsonUtil.jsonToBean(
					array.getJSONObject(i), PayAccountDto.class);
			list.add(item);
		}

		return list;
	}

	private JSONArray bookRecordArray(List<BookRecordDto> bookRecordList) {
		JSONArray array = new JSONArray();

		for (int i = 0; i < bookRecordList.size(); i++) {
			JSONObject item = JSONObject.fromObject(bookRecordList.get(i));
			item.put("dtoClass", bookRecordList.get(i).getClass().getName());
			if (bookRecordList.get(i).getTargetBookRecord() != null) {
				item.getJSONObject("targetBookRecord").put(
						"dtoClass",
						bookRecordList.get(i).getTargetBookRecord().getClass()
								.getName());
			}
			array.add(item);
		}

		return array;
	}

	/**
	 * 记账
	 */
	public String account(List<BookRecordDto> bookRecordList,
			OptRecordDto optRecord) throws Exception {
		JSONObject jsonData = new JSONObject();

		jsonData.put("optRecord", JSONObject.fromObject(optRecord));
		jsonData.put("bookRecordList", bookRecordArray(bookRecordList));

		NameValuePair[] nvps = {new NameValuePair("data", jsonData.toString()) };
		JSONObject json = doRequest("/finance/account", nvps);

		String batchId = json.getString("result");

		return batchId;
	}

	/**
	 * 创建支付表单 返回表单，包含操作批次号。
	 */
	public String createPayForm(String payAccountCode,
			List<BookRecordDto> targetBookRecordList, OptRecordDto optRecord)
			throws Exception {
		JSONObject jsonData = new JSONObject();

		jsonData.put("payAccountCode", payAccountCode);
		jsonData.put("optRecord", JSONObject.fromObject(optRecord));
		jsonData.put("targetBookRecordList",
				bookRecordArray(targetBookRecordList));

		NameValuePair[] nvps = {new NameValuePair("data", jsonData.toString()) };
		JSONObject json = doRequest("/finance/createPayForm", nvps);

		String form = json.getString("result");

		return form;
	}
	
	public void changeWithdrawalsStatus(WithdrawalsConditionDto condition)
			throws Exception {
		JSONObject jsonData = JSONObject.fromObject(condition);
		jsonData.put("dtoClass", condition.getClass().getName());

		NameValuePair[] nvps = {new NameValuePair("condition", jsonData.toString()) };
		JSONObject json = doRequest("/withdrawals/changeWithdrawalsStatus", nvps);
	}

	/**
	 * 创建提现请求支付表单 返回表单，包含操作批次号。
	 */
	public String createWithdrawalsPayForm(String payAccountCode,
			List<BookRecordDto> targetBookRecordList, WithdrawalsConditionDto withdrawalsCondition, OptRecordDto optRecord)
			throws Exception {
		JSONObject jsonData = new JSONObject();
		
		jsonData.put("payAccountCode", payAccountCode);
		jsonData.put("optRecord", JSONObject.fromObject(optRecord));
		jsonData.put("targetBookRecordList",
				bookRecordArray(targetBookRecordList));
		jsonData.put("withdrawalsCondition",  JSONObject.fromObject(withdrawalsCondition));
		jsonData.getJSONObject("withdrawalsCondition").put("dtoClass", withdrawalsCondition.getClass().getName());
		
		NameValuePair[] nvps = {new NameValuePair("data", jsonData.toString()) };
		JSONObject json = doRequest("/withdrawals/createPayForm", nvps);

		String form = json.getString("result");

		return form;
	}
	
	/**
	 * 查询账本明细
	 */
	public List<BookRecordDto> searchBookDetailList(BookConditionDto condition)
			throws Exception {
		JSONObject jsonData = JSONObject.fromObject(condition);
		jsonData.put("dtoClass", condition.getClass().getName());

		NameValuePair[] nvps = {new NameValuePair("condition", jsonData.toString()) };
		JSONObject json = doRequest("/finance/searchBookDetailList", nvps);

		JSONArray array = json.getJSONArray("result");

		String dtoClass = json.getJSONObject("property").getString("dtoClass");
		Class dtoCls = null;
		if (!"".equals(dtoClass)) {
			dtoCls = Class.forName(dtoClass);
		}

		List<BookRecordDto> list = new ArrayList<BookRecordDto>(array.size());
		for (int i = 0; i < array.size(); i++) {
			JSONObject item = array.getJSONObject(i);
			BookRecordDto c = (BookRecordDto) JsonUtil.jsonToBean(item, dtoCls);
			list.add(c);
		}

		return list;
	}

	
	private BookDto resetBookDto(BookDto book){
		
		if(book.getBalance() == null){
			book.setBalance(new BigDecimal(0));
		}
		
		if(book.getDebitAmount() == null){
			book.setDebitAmount(new BigDecimal(0));
		}
		
		if(book.getCreditAmount() == null){
			book.setCreditAmount(new BigDecimal(0));
		}
		
		if(book.getBalance() == null){
			book.setBalance(new BigDecimal(0));
		}
		
		if(book.getFrozenBalance() == null){
			book.setFrozenBalance(new BigDecimal(0));
		}
		
		return book;
	}
	
	
	/**
	 * 查询系统账本汇总金额
	 */
	public BookDto searchSysBookSummary() throws Exception {
		SysBookConditionDto condition = new SysBookConditionDto();

		JSONObject jsonData = JSONObject.fromObject(condition);
		jsonData.put("dtoClass", condition.getClass().getName());

		NameValuePair[] nvps = {new NameValuePair("condition", jsonData.toString()) };
		JSONObject json = doRequest("/finance/searchBookSummary", nvps);

		JSONArray array = json.getJSONArray("result");

		List<BookDto> list = new ArrayList<BookDto>(array.size());
		for (int i = 0; i < array.size(); i++) {
			JSONObject item = array.getJSONObject(i);
			BookDto c = (BookDto) JsonUtil.jsonToBean(item, BookDto.class);
			list.add(c);
		}

		if (list.size() == 0) {
			return resetBookDto(new BookDto());
		}

		return resetBookDto(list.get(0));
	}

	/**
	 * 查询企业应收账款账本汇总金额
	 */
	public BookDto searchEntReceivablesBookSummary(String entId) throws Exception {
		EntReceivablesBookConditionDto condition = new EntReceivablesBookConditionDto();
		condition.setEntId(entId);

		JSONObject jsonData = JSONObject.fromObject(condition);
		jsonData.put("dtoClass", condition.getClass().getName());

		NameValuePair[] nvps = {new NameValuePair("condition", jsonData.toString()) };
		JSONObject json = doRequest("/finance/searchBookSummary", nvps);

		JSONArray array = json.getJSONArray("result");

		List<BookDto> list = new ArrayList<BookDto>(array.size());
		for (int i = 0; i < array.size(); i++) {
			JSONObject item = array.getJSONObject(i);
			BookDto c = (BookDto) JsonUtil.jsonToBean(item, BookDto.class);
			list.add(c);
		}

		if (list.size() == 0) {
			return resetBookDto(new BookDto());
		}

		return resetBookDto(list.get(0));
	}

	/**
	 * 查询企业账本汇总金额
	 */
	public BookDto searchEntBookSummary(String entId) throws Exception {
		EntBookConditionDto condition = new EntBookConditionDto();
		condition.setEntId(entId);

		JSONObject jsonData = JSONObject.fromObject(condition);
		jsonData.put("dtoClass", condition.getClass().getName());

		NameValuePair[] nvps = {new NameValuePair("condition", jsonData.toString()) };
		JSONObject json = doRequest("/finance/searchBookSummary", nvps);

		JSONArray array = json.getJSONArray("result");

		List<BookDto> list = new ArrayList<BookDto>(array.size());
		for (int i = 0; i < array.size(); i++) {
			JSONObject item = array.getJSONObject(i);
			BookDto c = (BookDto) JsonUtil.jsonToBean(item, BookDto.class);
			list.add(c);
		}

		if (list.size() == 0) {
			return resetBookDto(new BookDto());
		}

		return resetBookDto(list.get(0));
	}

	/**
	 * 查询个人账本汇总金额
	 */
	public BookDto searchPerBookSummary(String perUserId) throws Exception {
		PerBookConditionDto condition = new PerBookConditionDto();
		condition.setPerUserId(perUserId);

		JSONObject jsonData = JSONObject.fromObject(condition);
		jsonData.put("dtoClass", condition.getClass().getName());

		NameValuePair[] nvps = {new NameValuePair("condition", jsonData.toString()) };
		JSONObject json = doRequest("/finance/searchBookSummary", nvps);

		JSONArray array = json.getJSONArray("result");

		List<BookDto> list = new ArrayList<BookDto>(array.size());
		for (int i = 0; i < array.size(); i++) {
			JSONObject item = array.getJSONObject(i);
			BookDto c = (BookDto) JsonUtil.jsonToBean(item, BookDto.class);
			list.add(c);
		}

		if (list.size() == 0) {
			return resetBookDto(new BookDto());
		}

		return resetBookDto(list.get(0));
	}

	/**
	 * 开票
	 */
	public String invoice(InvoiceRecordDto invoiceRecord) throws Exception {
		JSONObject jsonData = JSONObject.fromObject(invoiceRecord);
		jsonData.put("dtoClass", invoiceRecord.getClass().getName());

		NameValuePair[] nvps = {new NameValuePair("data", jsonData.toString()) };
		JSONObject json = doRequest("/invoice/doInvoice", nvps);

		String ret = json.getString("result");

		return ret;
	}

	/**
	 * 查询已开票记录
	 */
	public List<InvoiceRecordDto> searchInvoice(
			InvoiceConditionDto invoiceCondition) throws Exception {
		JSONObject jsonData = JSONObject.fromObject(invoiceCondition);
		jsonData.put("dtoClass", invoiceCondition.getClass().getName());

		NameValuePair[] nvps = {new NameValuePair("condition", jsonData.toString()) };
		JSONObject json = doRequest("/invoice/searchInvoice", nvps);

		JSONArray array = json.getJSONArray("result");

		String dtoClass = json.getJSONObject("property").getString("dtoClass");
		Class dtoCls = null;
		if (!"".equals(dtoClass)) {
			dtoCls = Class.forName(dtoClass);
		}

		List<InvoiceRecordDto> list = new ArrayList<InvoiceRecordDto>(
				array.size());
		for (int i = 0; i < array.size(); i++) {
			JSONObject item = array.getJSONObject(i);
			InvoiceRecordDto c = (InvoiceRecordDto) JsonUtil.jsonToBean(item, dtoCls);
			list.add(c);
		}

		return list;
	}

	/**
	 * 提现请求
	 */
	public String reqWithdrawals(WithdrawalsRequestDto record) throws Exception {
		JSONObject jsonData = JSONObject.fromObject(record);
		jsonData.put("dtoClass", record.getClass().getName());

		NameValuePair[] nvps = {new NameValuePair("data", jsonData.toString()) };
		JSONObject json = doRequest("/withdrawals/reqWithdrawals", nvps);

		String ret = json.getString("result");

		return ret;
	}



	/**
	 * 查询提现记录
	 */
	public List<WithdrawalsRequestDto> searchWithdrawalsReqList(
			WithdrawalsConditionDto condition) throws Exception {
		JSONObject jsonData = JSONObject.fromObject(condition);
		if(condition.getOptStatus() == null){
			jsonData.remove("optStatus");
		}
		if(condition.getOptStatusList() == null){
			jsonData.remove("optStatusList");
		}
		
		jsonData.put("dtoClass", condition.getClass().getName());

		NameValuePair[] nvps = {new NameValuePair("condition", jsonData.toString()) };
		JSONObject json = doRequest("/withdrawals/searchReqList", nvps);

		JSONArray array = json.getJSONArray("result");

		JSONObject property = json.getJSONObject("property");
		
		String dtoClass = property.getString("dtoClass");
		Class dtoCls = null;
		if (!"".equals(dtoClass)) {
			dtoCls = Class.forName(dtoClass);
		}

		if(property.has("count")){
			Long count = property.getLong("count");
			if(count != null){
				DbInterceptor.setResultCount(count.intValue());	
			}			
		}else{
			DbInterceptor.setResultCount(0);
		}
		
		
		List<WithdrawalsRequestDto> list = new ArrayList<WithdrawalsRequestDto>(
				array.size());
		for (int i = 0; i < array.size(); i++) {
			JSONObject item = array.getJSONObject(i);
			WithdrawalsRequestDto c = (WithdrawalsRequestDto) JsonUtil.jsonToBean(item,
					dtoCls);
			list.add(c);
		}

		return list;
	}

	
	
	
	private WithdrawalsRequestDto resetWithdrawalsRequestDto(WithdrawalsRequestDto dto){

		if(dto.getAmount() == null){
			dto.setAmount(new BigDecimal(0));
		}
		
		return dto;
	}
	
	/**
	 * 查询提现记录
	 */
	public WithdrawalsRequestDto searchWithdrawalsRequestSummary(
			WithdrawalsConditionDto condition) throws Exception {
		JSONObject jsonData = JSONObject.fromObject(condition);
		jsonData.put("dtoClass", condition.getClass().getName());
		if(condition.getOptStatus() == null){
			jsonData.remove("optStatus");
		}
		if(condition.getOptStatusList() == null){
			jsonData.remove("optStatusList");
		}
		
		NameValuePair[] nvps = {new NameValuePair("condition", jsonData.toString()) };
		JSONObject json = doRequest("/withdrawals/searchReqSummary", nvps);

		JSONObject result = json.getJSONObject("result");
		
		String dtoClass = json.getJSONObject("property").getString("dtoClass");
		Class dtoCls = null;
		if (!"".equals(dtoClass)) {
			dtoCls = Class.forName(dtoClass);
		}

		WithdrawalsRequestDto c = null;
		
		if(result != null  && !result.isNullObject()){
			c = (WithdrawalsRequestDto) JsonUtil.jsonToBean(result,
					dtoCls);	
		}else{
			c = (WithdrawalsRequestDto)dtoCls.newInstance();
		}


		return resetWithdrawalsRequestDto(c);
	}
	
}
