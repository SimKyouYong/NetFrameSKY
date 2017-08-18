package co.kr.sky.model;

/**
 * 계약정보
 * @author SPKOO
 */
public class SubscriptionInfo {
	
	/**
	 * 고객이름
	 */
	private String customerName;
	/**
	 * 고객민번
	 */
	private String customerResidentNumber;
	/**
	 * 고객연락처
	 */
	private String customerPhoneNumber;
	/**
	 * 고객메일
	 */
	private String customerEmail;
	/**
	 * 성별 남:0 여:1
	 */
	private String genderType;
	/**
	 * 성년 미성년 여부  0:성년, 1:미성년, 2:15세 이상 미성년
	 */
	private String minorType;
	/**
	 * 부모타입<br>
	 * 성년:공백, 양친:0, 편부:1, 편모:2, 부재:3, 결혼:4
	 */
	private String parentsType;
	/**
	 * 형제자매일 경우 1, 그외의 경우는 0
	 */
	private String brotherCheck;
	/**
	 * 자녀 있을경우:1, 없을경우:0
	 */
	private String child1Check,child2Check,child3Check;
	/**
	 * 지정대리인청구인여부(있으면:1, 없으면:0)
	 */
	private String agentCheck;
	/**
	 * EDD여부(표준:0, 강화:1)
	 */
	private String eddCheck;
	/**
	 * 외국인여부(내국인:0, 외국인:1)
	 */
	private String foreignCheck;
	/**
	 *  지정대리인청구인_자택/직장(지정대리인청구인 없을경우:공백, 자택:0, 직장:1)
	 */
	private String agentAddressType;
	/**
	 * Main FC 코드
	 */
	private String fcCode;
	/**
	 * Main FC 성명
	 */
	private String fcName;
	/**
	 * 청약건에 대한 고유 식별자
	 */
	private String documentId;
	/**
	 * 청약번호
	 */
	private String subscriptionNo;
	/**
	 * 청약타입(신규:0, 심사보완:1, 기타:2)
	 */
	private String subscriptionType;
	/**
	 * 전자문서_통합페이지수(초기 XML에서는 공백이며 최종 XML 전송시 값을 채워서 전달)
	 */
	private String documnetTotalPageCount;
	/**
	 * 상품명
	 */
	private String productName;
	/**
	 * 상품코드
	 */
	private String productCode;
	/**
	 * 변액보험여부(일반:0, 변액:1)
	 */
	private String variableCheck;
	/**
	 * 변액보험분류(변액이 아닐경우:공백, 변액보장형:0, 변액연금형:1, 변액저축형:2)
	 */
	private String variableType;
	/**
	 * 실손보험여부(일반:0, 실손:1)
	 */
	private String indemnityCheck;
	/**
	 * 즉시연금보험분류(즉시연금보험이 아닌경우:공백, 즉시형:1, 거치형:2)
	 */
	private String immediateType;
	/**
	 * 비교안내문 타사포함 여부
	 */
	private String otherComCheck;
	/**
	 * 메인 기여율
	 */
	private String mainRatio;
	/**
	 * 서브 기여율
	 */
	private String coRatio;
	/**
	 * 자녀 여부 1,2,3
	 * @return
	 */
	private String insured03Type, insured04Type, insured05Type;
	/**
	 * 신계약 주요사항 안심체크 (셀프모니터링) 
	 * @return
	 */
	private String selfMonTgtYn;
	/**
	 * 임신 주수 - 23개월 이내 인지 여부 체크  01:23주 이하 02:23주 초과 
	 * @return
	 */
	private String pregnancyTerm;
	/**
	 * 예금주 ( 0:있음 1:없음 )
	 * @return
	 */
	private String acc_owner_self_yn;
	/**
	 * 수익자1,2,3,4 ( 0:없음 1:있음 )
	 * @return
	 */
	private String benefit_yn1;
	private String benefit_yn2;
	private String benefit_yn3;
	private String benefit_yn4;
	
	
	/*FC 관련 추가(농협)*/
	private String sm_phone_number;
	/*상품 관련 추가(농협)*/
	private String variable_kind;
	private String ci_check;
	
	/*기타 추가(농협)*/
	private String insured_count;
	private String reg_user_no;
	private String reg_dept_no;
	private String incomplete_type_cd;
	private String incomplete_seq;
	private String payment_method;

	/*상품설명서 추가(농협)*/
	private String parents_phone1;
	private String parents_phone2;

	/*청약서 (공통) 추가(농협)*/
	private String sms_yn;
	private String info_type;
	private String first_fee_bank;
	private String first_fee_acnt_no;
	private String first_fee_owner;
	private String acnt_yyyy;
	private String acnt_mm;
	private String acnt_dd;
	private String acnt_apm;
	private String acnt_hh;
	private String acnt_mi;
	private String second_fee_bank;
	private String second_fee_acnt_no;
	private String second_fee_owner;
	private String pay_hope_day;
	private String immediate_annuity;

	/*FATCA 추가(농협)*/
	private String customer_check;
	private String eng_last_name;
	private String eng_first_name;
	private String tax_no;
	private String eng_addr;

	public String getSm_phone_number() {
		return sm_phone_number;
	}
	public void setSm_phone_number(String sm_phone_number) {
		this.sm_phone_number = sm_phone_number;
	}
	public String getVariable_kind() {
		return variable_kind;
	}
	public void setVariable_kind(String variable_kind) {
		this.variable_kind = variable_kind;
	}
	public String getCi_check() {
		return ci_check;
	}
	public void setCi_check(String ci_check) {
		this.ci_check = ci_check;
	}
	public String getInsured_count() {
		return insured_count;
	}
	public void setInsured_count(String insured_count) {
		this.insured_count = insured_count;
	}
	public String getReg_user_no() {
		return reg_user_no;
	}
	public void setReg_user_no(String reg_user_no) {
		this.reg_user_no = reg_user_no;
	}
	public String getReg_dept_no() {
		return reg_dept_no;
	}
	public void setReg_dept_no(String reg_dept_no) {
		this.reg_dept_no = reg_dept_no;
	}
	public String getIncomplete_type_cd() {
		return incomplete_type_cd;
	}
	public void setIncomplete_type_cd(String incomplete_type_cd) {
		this.incomplete_type_cd = incomplete_type_cd;
	}
	public String getIncomplete_seq() {
		return incomplete_seq;
	}
	public void setIncomplete_seq(String incomplete_seq) {
		this.incomplete_seq = incomplete_seq;
	}
	public String getPayment_method() {
		return payment_method;
	}
	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}
	public String getParents_phone1() {
		return parents_phone1;
	}
	public void setParents_phone1(String parents_phone1) {
		this.parents_phone1 = parents_phone1;
	}
	public String getParents_phone2() {
		return parents_phone2;
	}
	public void setParents_phone2(String parents_phone2) {
		this.parents_phone2 = parents_phone2;
	}
	public String getSms_yn() {
		return sms_yn;
	}
	public void setSms_yn(String sms_yn) {
		this.sms_yn = sms_yn;
	}
	public String getInfo_type() {
		return info_type;
	}
	public void setInfo_type(String info_type) {
		this.info_type = info_type;
	}
	public String getFirst_fee_bank() {
		return first_fee_bank;
	}
	public void setFirst_fee_bank(String first_fee_bank) {
		this.first_fee_bank = first_fee_bank;
	}
	public String getFirst_fee_acnt_no() {
		return first_fee_acnt_no;
	}
	public void setFirst_fee_acnt_no(String first_fee_acnt_no) {
		this.first_fee_acnt_no = first_fee_acnt_no;
	}
	public String getFirst_fee_owner() {
		return first_fee_owner;
	}
	public void setFirst_fee_owner(String first_fee_owner) {
		this.first_fee_owner = first_fee_owner;
	}
	public String getAcnt_yyyy() {
		return acnt_yyyy;
	}
	public void setAcnt_yyyy(String acnt_yyyy) {
		this.acnt_yyyy = acnt_yyyy;
	}
	public String getAcnt_mm() {
		return acnt_mm;
	}
	public void setAcnt_mm(String acnt_mm) {
		this.acnt_mm = acnt_mm;
	}
	public String getAcnt_dd() {
		return acnt_dd;
	}
	public void setAcnt_dd(String acnt_dd) {
		this.acnt_dd = acnt_dd;
	}
	public String getAcnt_apm() {
		return acnt_apm;
	}
	public void setAcnt_apm(String acnt_apm) {
		this.acnt_apm = acnt_apm;
	}
	public String getAcnt_hh() {
		return acnt_hh;
	}
	public void setAcnt_hh(String acnt_hh) {
		this.acnt_hh = acnt_hh;
	}
	public String getAcnt_mi() {
		return acnt_mi;
	}
	public void setAcnt_mi(String acnt_mi) {
		this.acnt_mi = acnt_mi;
	}
	public String getSecond_fee_bank() {
		return second_fee_bank;
	}
	public void setSecond_fee_bank(String second_fee_bank) {
		this.second_fee_bank = second_fee_bank;
	}
	public String getSecond_fee_acnt_no() {
		return second_fee_acnt_no;
	}
	public void setSecond_fee_acnt_no(String second_fee_acnt_no) {
		this.second_fee_acnt_no = second_fee_acnt_no;
	}
	public String getSecond_fee_owner() {
		return second_fee_owner;
	}
	public void setSecond_fee_owner(String second_fee_owner) {
		this.second_fee_owner = second_fee_owner;
	}
	public String getPay_hope_day() {
		return pay_hope_day;
	}
	public void setPay_hope_day(String pay_hope_day) {
		this.pay_hope_day = pay_hope_day;
	}
	public String getImmediate_annuity() {
		return immediate_annuity;
	}
	public void setImmediate_annuity(String immediate_annuity) {
		this.immediate_annuity = immediate_annuity;
	}
	public String getCustomer_check() {
		return customer_check;
	}
	public void setCustomer_check(String customer_check) {
		this.customer_check = customer_check;
	}
	public String getEng_last_name() {
		return eng_last_name;
	}
	public void setEng_last_name(String eng_last_name) {
		this.eng_last_name = eng_last_name;
	}
	public String getEng_first_name() {
		return eng_first_name;
	}
	public void setEng_first_name(String eng_first_name) {
		this.eng_first_name = eng_first_name;
	}
	public String getTax_no() {
		return tax_no;
	}
	public void setTax_no(String tax_no) {
		this.tax_no = tax_no;
	}
	public String getEng_addr() {
		return eng_addr;
	}
	public void setEng_addr(String eng_addr) {
		this.eng_addr = eng_addr;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getBranch_name() {
		return branch_name;
	}
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
	public String getBranch_phone_no() {
		return branch_phone_no;
	}
	public void setBranch_phone_no(String branch_phone_no) {
		this.branch_phone_no = branch_phone_no;
	}

	/*우수고객 특별인증 신청서 추가(농협)*/
	private String addr;
	private String branch_name;
	private String branch_phone_no;

	
	
	
	public String getAcc_owner_self_yn() {
		return acc_owner_self_yn;
	}
	public void setAcc_owner_self_yn(String acc_owner_self_yn) {
		this.acc_owner_self_yn = acc_owner_self_yn;
	}
	public String getBenefit_yn1() {
		return benefit_yn1;
	}
	public void setBenefit_yn1(String benefit_yn1) {
		this.benefit_yn1 = benefit_yn1;
	}
	public String getBenefit_yn2() {
		return benefit_yn2;
	}
	public void setBenefit_yn2(String benefit_yn2) {
		this.benefit_yn2 = benefit_yn2;
	}
	public String getBenefit_yn3() {
		return benefit_yn3;
	}
	public void setBenefit_yn3(String benefit_yn3) {
		this.benefit_yn3 = benefit_yn3;
	}
	public String getBenefit_yn4() {
		return benefit_yn4;
	}
	public void setBenefit_yn4(String benefit_yn4) {
		this.benefit_yn4 = benefit_yn4;
	}
	public String getPregnancyTerm() {
		return pregnancyTerm;
	}
	public void setPregnancyTerm(String pregnancyTerm) {
		this.pregnancyTerm = pregnancyTerm;
	}
	public String getSelfMonTgtYn() {
		return selfMonTgtYn;
	}
	public void setSelfMonTgtYn(String selfMonTgtYn) {
		this.selfMonTgtYn = selfMonTgtYn;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerResidentNumber() {
		return customerResidentNumber;
	}
	public void setCustomerResidentNumber(String customerResidentNumber) {
		this.customerResidentNumber = customerResidentNumber;
	}
	public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}
	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getGenderType() {
		return genderType;
	}
	public void setGenderType(String genderType) {
		this.genderType = genderType;
	}
	public String getMinorType() {
		return minorType;
	}
	public void setMinorType(String minorType) {
		this.minorType = minorType;
	}
	public String getParentsType() {
		return parentsType;
	}
	public void setParentsType(String parentsType) {
		this.parentsType = parentsType;
	}
	public String getBrotherCheck() {
		return brotherCheck;
	}
	public void setBrotherCheck(String brotherCheck) {
		this.brotherCheck = brotherCheck;
	}
	public String getChild1Check() {
		return child1Check;
	}
	public void setChild1Check(String child1Check) {
		this.child1Check = child1Check;
	}
	public String getChild2Check() {
		return child2Check;
	}
	public void setChild2Check(String child2Check) {
		this.child2Check = child2Check;
	}
	public String getChild3Check() {
		return child3Check;
	}
	public void setChild3Check(String child3Check) {
		this.child3Check = child3Check;
	}
	
	public String getEddCheck() {
		return eddCheck;
	}
	public void setEddCheck(String eddCheck) {
		this.eddCheck = eddCheck;
	}
	public String getAgentAddressType() {
		return agentAddressType;
	}
	public void setAgentAddressType(String agentAddressType) {
		this.agentAddressType = agentAddressType;
	}
	public String getFcCode() {
		return fcCode;
	}
	public void setFcCode(String fcCode) {
		this.fcCode = fcCode;
	}
	public String getFcName() {
		return fcName;
	}
	public void setFcName(String fcName) {
		this.fcName = fcName;
	}
	public String getDocumentId() {
		return documentId;
	}
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	public String getSubscriptionNo() {
		return subscriptionNo;
	}
	public void setSubscriptionNo(String subscriptionNo) {
		this.subscriptionNo = subscriptionNo;
	}
	public String getSubscriptionType() {
		return subscriptionType;
	}
	public void setSubscriptionType(String subscriptionType) {
		this.subscriptionType = subscriptionType;
	}
	public String getDocumnetTotalPageCount() {
		return documnetTotalPageCount;
	}
	public void setDocumnetTotalPageCount(String documnetTotalPageCount) {
		this.documnetTotalPageCount = documnetTotalPageCount;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getVariableCheck() {
		return variableCheck;
	}
	public void setVariableCheck(String variableCheck) {
		this.variableCheck = variableCheck;
	}
	public String getVariableType() {
		return variableType;
	}
	public void setVariableType(String variableType) {
		this.variableType = variableType;
	}
	public String getIndemnityCheck() {
		return indemnityCheck;
	}
	public void setIndemnityCheck(String indemnityCheck) {
		this.indemnityCheck = indemnityCheck;
	}
	public String getImmediateType() {
		return immediateType;
	}
	public void setImmediateType(String immediateType) {
		this.immediateType = immediateType;
	}
	public String getAgentCheck() {
		return agentCheck;
	}
	public void setAgentCheck(String agentCheck) {
		this.agentCheck = agentCheck;
	}
	public String getForeignCheck() {
		return foreignCheck;
	}
	public void setForeignCheck(String foreignCheck) {
		this.foreignCheck = foreignCheck;
	}
	public String getOtherComCheck() {
		return otherComCheck;
	}
	public void setOtherComCheck(String otherComCheck) {
		this.otherComCheck = otherComCheck;
	}
	public String getMainRatio() {
		return mainRatio;
	}
	public void setMainRatio(String mainRatio) {
		this.mainRatio = mainRatio;
	}
	public String getCoRatio() {
		return coRatio;
	}
	public void setCoRatio(String subRatio) {
		this.coRatio = subRatio;
	}
	public String getInsured03Type() {
		return insured03Type;
	}
	public void setInsured03Type(String insured03Type) {
		this.insured03Type = insured03Type;
	}
	public String getInsured04Type() {
		return insured04Type;
	}
	public void setInsured04Type(String insured04Type) {
		this.insured04Type = insured04Type;
	}
	public String getInsured05Type() {
		return insured05Type;
	}
	public void setInsured05Type(String insured05Type) {
		this.insured05Type = insured05Type;
	}
		
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SubscriptionInfo [customerName=");
		builder.append(customerName);
		builder.append(", customerResidentNumber=");
		builder.append(customerResidentNumber);
		builder.append(", customerPhoneNumber=");
		builder.append(customerPhoneNumber);
		builder.append(", customerEmail=");
		builder.append(customerEmail);
		builder.append(", genderType=");
		builder.append(genderType);
		builder.append(", minorType=");
		builder.append(minorType);
		builder.append(", parentsType=");
		builder.append(parentsType);
		builder.append(", brotherCheck=");
		builder.append(brotherCheck);
		builder.append(", child1Check=");
		builder.append(child1Check);
		builder.append(", child2Check=");
		builder.append(child2Check);
		builder.append(", child3Check=");
		builder.append(child3Check);
		builder.append(", agentCheck=");
		builder.append(agentCheck);
		builder.append(", eddCheck=");
		builder.append(eddCheck);
		builder.append(", foreignCheck=");
		builder.append(foreignCheck);
		builder.append(", agentAddressType=");
		builder.append(agentAddressType);
		builder.append(", fcCode=");
		builder.append(fcCode);
		builder.append(", fcName=");
		builder.append(fcName);
		builder.append(", documentId=");
		builder.append(documentId);
		builder.append(", subscriptionNo=");
		builder.append(subscriptionNo);
		builder.append(", subscriptionType=");
		builder.append(subscriptionType);
		builder.append(", documnetTotalPageCount=");
		builder.append(documnetTotalPageCount);
		builder.append(", productName=");
		builder.append(productName);
		builder.append(", productCode=");
		builder.append(productCode);
		builder.append(", variableCheck=");
		builder.append(variableCheck);
		builder.append(", variableType=");
		builder.append(variableType);
		builder.append(", indemnityCheck=");
		builder.append(indemnityCheck);
		builder.append(", immediateType=");
		builder.append(immediateType);
		builder.append(", otherComCheck=");
		builder.append(otherComCheck);
		builder.append(", mainRatio=");
		builder.append(mainRatio);
		builder.append(", coRatio=");
		builder.append(coRatio);
		builder.append(", insured03Type=");
		builder.append(insured03Type);
		builder.append(", insured04Type=");
		builder.append(insured04Type);
		builder.append(", insured05Type=");
		builder.append(insured05Type);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
