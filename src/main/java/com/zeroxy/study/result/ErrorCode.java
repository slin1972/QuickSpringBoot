package com.zeroxy.study.result;


import com.zeroxy.CommonResult;

public enum ErrorCode {
	OK_0(new CommonResult(0, "OK")),
	
	PADDING(new CommonResult(10,"Padding...")),
	REQUESTED(new CommonResult(11,"Requested...")),
	ACCEPTED(new CommonResult(12,"Accepted...")), 
	REJECT(new CommonResult(13,"Reject...")) ,
	
	ERROR_999(new CommonResult(999,"Unknown exception.")),
	ERROR_100(new CommonResult(100,"Params validate error.")),
	ERROR_200(new CommonResult(200,"Server exception.")),
	ERROR_300(new CommonResult(300,"Access Error.")),
	ERROR_301(new CommonResult(301,"Uid is empty or format error.")),
	ERROR_302(new CommonResult(302,"Av or et or os or m or dpi or imei is empty or format error.")),
	ERROR_303(new CommonResult(303,"Token expire.")),
	ERROR_304(new CommonResult(304,"Did is empty or format error.")),
	ERROR_305(new CommonResult(305,"Bid is empty or format error.")),
	ERROR_306(new CommonResult(306,"Did is empty or format error.")),
	ERROR_308(new CommonResult(308,"Function already close or temporary close.")),
	ERROR_309(new CommonResult(309,"Account login in other address.")),
	ERROR_310(new CommonResult(310,"The link expire.")),
	ERROR_101(new CommonResult(101,"Empty phone or password")),
	ERROR_102(new CommonResult(102,"Wrong phone")),
	ERROR_103(new CommonResult(103,"Wrong password")),
	ERROR_104(new CommonResult(104,"This phone number has been registered.")),
	ERROR_153(new CommonResult(153,"The phone or areaCode format error.")),
	ERROR_105(new CommonResult(105,"Invalid code.")),
	ERROR_106(new CommonResult(106,"Register failed.")),
	ERROR_107(new CommonResult(107,"Not associated with the account.")),
	ERROR_108(new CommonResult(108,"Not associated with the account.")),
	ERROR_109(new CommonResult(109,"Device add failed.")),
	ERROR_134(new CommonResult(134,"Device list not found in unkownDevices.")),
	ERROR_110(new CommonResult(110,"BabyProfile save failed.")),
	ERROR_111(new CommonResult(111,"Query the user is not the baby relatives.")),
	ERROR_139(new CommonResult(139,"Query the user is not the baby administrator.")),
	ERROR_135(new CommonResult(135,"Device not exist.")),
	ERROR_136(new CommonResult(136,"You do not focus on this baby.")),
	ERROR_112(new CommonResult(112,"Device sn already exists in your device list.")),
	ERROR_132(new CommonResult(132,"Device sn already exists in other person device list.")),
	ERROR_113(new CommonResult(113,"Baby json format error.")),
	ERROR_114(new CommonResult(114,"Config json format error.")),
	ERROR_115(new CommonResult(115,"Device config save failed.")),
	ERROR_116(new CommonResult(116,"Page json format error.")),
	ERROR_117(new CommonResult(117,"Did contains babyid not equlas bid.")),
	ERROR_118(new CommonResult(118,"Invite add failed.")),
	ERROR_119(new CommonResult(119,"Invite already exists, and has been accepted.")),
	ERROR_120(new CommonResult(120,"Invite already exists, is waiting to accept.")),
	ERROR_121(new CommonResult(121,"Sar json format error.")),
	ERROR_122(new CommonResult(122,"Safe region add failed.")),
	ERROR_129(new CommonResult(129,"Safe region name repeat.")),
	ERROR_130(new CommonResult(130,"Safe region latlng repeat.")),
	ERROR_131(new CommonResult(131,"Safe region not exists.")),
	ERROR_123(new CommonResult(123,"Feedback json format error.")),
	ERROR_124(new CommonResult(124,"Feedback add failed.")),
	ERROR_125(new CommonResult(125,"Nick and relateName all null.")),
	ERROR_126(new CommonResult(126,"Set failed.")),
	ERROR_127(new CommonResult(127,"Device command busy.")),
	ERROR_128(new CommonResult(128,"Device command response timeout.")),
	ERROR_133(new CommonResult(133,"Device disconnect.")),
	ERROR_137(new CommonResult(137,"FlowerIndex !> FlowerCount.")),
	ERROR_138(new CommonResult(138,"Flower json format error.")),
	ERROR_140(new CommonResult(140,"Email send rancode code failed.")),
	ERROR_141(new CommonResult(141,"SMS send rancode code failed.")),
	ERROR_142(new CommonResult(142,"SMS function close.")),
	ERROR_143(new CommonResult(143,"Parma-->phone is empty.")),
	ERROR_144(new CommonResult(144,"Email is empty.")),
	ERROR_145(new CommonResult(145,"Area code is empty.")),
	ERROR_146(new CommonResult(146,"More then max count.")),
	ERROR_169(new CommonResult(169,"Device no format error.")),
	ERROR_147(new CommonResult(147,"Can't invite youself.")),
	ERROR_148(new CommonResult(148,"Invite not exists.")),
	ERROR_149(new CommonResult(149,"The two time to get verification interval is too short.")),
	ERROR_150(new CommonResult(150,"Email format error.")),
	ERROR_151(new CommonResult(151,"There is no data in the system.Please check.")),
	ERROR_152(new CommonResult(152,"Within 24 hours of each phone number can only get five verification code.")),
	ERROR_154(new CommonResult(154,"The number of security zones allowed to add is 20.")),
	ERROR_155(new CommonResult(155,"Wrong invite code.")),
	ERROR_156(new CommonResult(156,"Mailbox is not activated.")),
	ERROR_157(new CommonResult(157,"File upload failed.")),
	ERROR_158(new CommonResult(158,"Not found the device of the iccid in System.")),
	ERROR_159(new CommonResult(159,"Location correct json format error.")),
	ERROR_160(new CommonResult(160,"Topic not exists.")),
	ERROR_161(new CommonResult(161,"Topic json format error.")),
	ERROR_162(new CommonResult(162,"The current request is not a multipart request.")),
	ERROR_163(new CommonResult(163,"Contact json format error.")),
	ERROR_164(new CommonResult(164,"The number of contacts allowed to add is 10.")),
	ERROR_165(new CommonResult(165,"Contacts repeat.")),
	ERROR_166(new CommonResult(166,"The watch of the version must be more than 2.")),
	ERROR_167(new CommonResult(167,"Topic into the audit status.")),
	ERROR_168(new CommonResult(168,"Topic contains pornographic or illegal information.")),

	ERROR_170(new CommonResult(170,"Alarm json format error.")),
	ERROR_171(new CommonResult(171,"Topic frequent operation.")),
	ERROR_172(new CommonResult(172,"Comment Frequent operation.")),
	ERROR_173(new CommonResult(173,"Topic publish time interval is too short.")),
	ERROR_174(new CommonResult(174,"Comment publish time interval is too short.")),
	ERROR_1000(new CommonResult(1000,"No data."));
	CommonResult commonResult ;
	
	ErrorCode(CommonResult commonResult){
		this.commonResult = commonResult ;
	}

	public CommonResult getCommonResult() {
		return commonResult;
	}

	public void setCommonResult(CommonResult commonResult) {
		this.commonResult = commonResult;
	}
}
	
	
