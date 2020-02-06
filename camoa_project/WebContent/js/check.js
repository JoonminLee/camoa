
$(document).ready(function(){
	// 검증에 사용할 함수명들을 배열에 담아준다.
	var idFuncArray = ["isAlphabetForSpan", "spaceCheck"];

	// 1. span태그 obj, 2. input태그 obj, 3. 위에서 정의한 함수명 배열, 4. 검증에 걸렸을 때 나타날 텍스트, 5. 검증을 통과했을 때 나타날 텍스트, 6. span태그의 좌측 폭 위치.
	spanValidation($("#idSpan"), $("#userId"), idFuncArray, "아이디는 영문으로 공백없이 입력하십시오!", "사용 가능한 형식의 아이디입니다.", "15px");
});

$(document).ready(function(){
	// 검증에 사용할 함수명들을 배열에 담아준다.
	var pwdFuncArray = ["chkPwd", "spaceCheck"];

	// 1. span태그 obj, 2. input태그 obj, 3. 위에서 정의한 함수명 배열, 4. 검증에 걸렸을 때 나타날 텍스트, 5. 검증을 통과했을 때 나타날 텍스트, 6. span태그의 좌측 폭 위치.
	spanValidation2($("#pwdSpan"), $("#userPwd1"), pwdFuncArray, "비번은 영문,숫자 혼합 공백없이 6~20자이내로 입력하시오!", "사용 가능한 형식의 비밀번호입니다.", "15px");
});
  $(document).ready(function(){
	// 검증에 사용할 함수명들을 배열에 담아준다.
	var pwd2FuncArray = ["pwdSame"];

	// 1. span태그 obj, 2. input태그 obj, 3. 위에서 정의한 함수명 배열, 4. 검증에 걸렸을 때 나타날 텍스트, 5. 검증을 통과했을 때 나타날 텍스트, 6. span태그의 좌측 폭 위치.
	spanValidation3($("#pwdSpan2"), $("#userPwd1"),$("#userPwd2"), pwd2FuncArray, "비밀번호가 불일치합니다!", "비밀번호가 일치합니다.", "15px");
});  
  $(document).ready(function(){
		// 검증에 사용할 함수명들을 배열에 담아준다.
		var phoneFuncArray = ["phoneCheck", "spaceCheck"];

		// 1. span태그 obj, 2. input태그 obj, 3. 위에서 정의한 함수명 배열, 4. 검증에 걸렸을 때 나타날 텍스트, 5. 검증을 통과했을 때 나타날 텍스트, 6. span태그의 좌측 폭 위치.
		spanValidation4($("#numSpan"), $("#userNum"), phoneFuncArray, "올바른 핸드폰 형식을 기입하시오!", "올바른 핸드폰 형식입니다.", "15px");
	}); 
  function spanValidation4(spanObj, inputObj, validFuncArray, redMsg, greenMsg, marginLeftPx){
		spanObj.css("margin-left", marginLeftPx); // span태그의 좌측 폭을 설정해준다.
		
		var confirmCheck = false; // 검증에 통과 여부에 사용할 flag
		
		spanObj.hide(); // span태그를 숨긴다.
		
		inputObj.bind('focusin keyup', function(){ // input태그에 포커스가 들어오거나 키가 눌렸을 때 실행됨
			var inputValue = inputObj.val();
			
			var funcResult = true; // 함수 실행 결과를 담을 flag
			
			for(i=0; i<validFuncArray.length; i++){ // 검증에 사용할 함수명 배열을 반복문으로 돌린다.
				var funcName = validFuncArray[i]; // 배열에서 함수명을 하나씩 뽑아낸다. 
				var funcObj = window[funcName]; // 함수명(string)을 객체(object)로 받는다.
				funcResult = funcObj(inputValue); // 해당 함수를 실행하여 결과값(true/false)을 변수에 담는다. 만약 함수 하나라도 통과를 하지 못하면 false가 된다.
				if(!funcResult){ // 검증에 통과하지 못한 함수가 있을 경우 반복문 탈출
					break;
				}
			}
			
			if(!funcResult){ // 검증에 통과하지 못했을 때,
				spanObj.show(); // span태그 보여준다.
				spanObj.removeClass('greenText'); // span태그에 greenText 클래스를 삭제한다.
				spanObj.addClass('redText'); // span태그에 redText 클래스를 추가한다.
				
				spanObj.text(""); //  span태그의 텍스트를 지운다.
				spanObj.append(redMsg); // span태그에  검증에 통과하지 못했을 때 나타나는 텍스트를 넣는다.
				
					 
				confirmCheck = false; // 검증 통과 여부 flag에 false를 대입한다.
			}else{ // 검증에 통과했을 때,
				spanObj.show();
				spanObj.removeClass('redText');
				spanObj.addClass('greenText');
				
				spanObj.text("");
				spanObj.append(greenMsg);
			
					
				confirmCheck = true;
			}
			
		});
		
		inputObj.focusout(function(){ // 포커스가 input태그에서 벗어났을 때 실행,
			var inputValue = inputObj.val();
			if(confirmCheck || inputValue == ""){ // 검증에 통과를 했거나 input태그에 입력 값이 없을 경우,
				spanObj.hide(); // span태그를 숨긴다.
			}
		});
	}
  
function spanValidation2(spanObj, inputObj, validFuncArray, redMsg, greenMsg, marginLeftPx){
	spanObj.css("margin-left", marginLeftPx); // span태그의 좌측 폭을 설정해준다.
	
	var confirmCheck = false; // 검증에 통과 여부에 사용할 flag
	
	spanObj.hide(); // span태그를 숨긴다.
	
	inputObj.bind('focusin keyup', function(){ // input태그에 포커스가 들어오거나 키가 눌렸을 때 실행됨
		var inputValue = inputObj.val();
		
		var funcResult = true; // 함수 실행 결과를 담을 flag
		
		for(i=0; i<validFuncArray.length; i++){ // 검증에 사용할 함수명 배열을 반복문으로 돌린다.
			var funcName = validFuncArray[i]; // 배열에서 함수명을 하나씩 뽑아낸다. 
			var funcObj = window[funcName]; // 함수명(string)을 객체(object)로 받는다.
			funcResult = funcObj(inputValue); // 해당 함수를 실행하여 결과값(true/false)을 변수에 담는다. 만약 함수 하나라도 통과를 하지 못하면 false가 된다.
			if(!funcResult){ // 검증에 통과하지 못한 함수가 있을 경우 반복문 탈출
				break;
			}
		}
		
		if(!funcResult){ // 검증에 통과하지 못했을 때,
			spanObj.show(); // span태그 보여준다.
			spanObj.removeClass('greenText'); // span태그에 greenText 클래스를 삭제한다.
			spanObj.addClass('redText'); // span태그에 redText 클래스를 추가한다.
			
			spanObj.text(""); //  span태그의 텍스트를 지운다.
			spanObj.append(redMsg); // span태그에  검증에 통과하지 못했을 때 나타나는 텍스트를 넣는다.
			
			/* submit.prop("disabled", true);
			submit.attr("disabled","disabled"); */
				 
			confirmCheck = false; // 검증 통과 여부 flag에 false를 대입한다.
		}else{ // 검증에 통과했을 때,
			spanObj.show();
			spanObj.removeClass('redText');
			spanObj.addClass('greenText');
			
			spanObj.text("");
			spanObj.append(greenMsg);
		/* 	submit.prop("disabled", false);
			submit.removeAttr("disabled"); */
				
			confirmCheck = true;
		}
		
	});
	
	inputObj.focusout(function(){ // 포커스가 input태그에서 벗어났을 때 실행,
		var inputValue = inputObj.val();
		if(confirmCheck || inputValue == ""){ // 검증에 통과를 했거나 input태그에 입력 값이 없을 경우,
			spanObj.hide(); // span태그를 숨긴다.
		}
	});
}
function spanValidation3(spanObj, inputObj,inputObj2, validFuncArray, redMsg, greenMsg, marginLeftPx){
	spanObj.css("margin-left", marginLeftPx); // span태그의 좌측 폭을 설정해준다.
	
	var confirmCheck = false; // 검증에 통과 여부에 사용할 flag
	
	spanObj.hide(); // span태그를 숨긴다.
	
	inputObj2.bind('focusin keyup', function(){ // input태그에 포커스가 들어오거나 키가 눌렸을 때 실행됨
		var inputValue = inputObj.val();
		var inputValue2 = inputObj2.val();
		var funcResult = true; // 함수 실행 결과를 담을 flag
		
		for(i=0; i<validFuncArray.length; i++){ // 검증에 사용할 함수명 배열을 반복문으로 돌린다.
			var funcName = validFuncArray[i]; // 배열에서 함수명을 하나씩 뽑아낸다. 
			var funcObj = window[funcName]; // 함수명(string)을 객체(object)로 받는다.
			funcResult = funcObj(inputValue, inputValue2); // 해당 함수를 실행하여 결과값(true/false)을 변수에 담는다. 만약 함수 하나라도 통과를 하지 못하면 false가 된다.
			if(!funcResult){ // 검증에 통과하지 못한 함수가 있을 경우 반복문 탈출
				break;
			}
		}
		
		if(!funcResult){ // 검증에 통과하지 못했을 때,
			spanObj.show(); // span태그 보여준다.
			spanObj.removeClass('greenText'); // span태그에 greenText 클래스를 삭제한다.
			spanObj.addClass('redText'); // span태그에 redText 클래스를 추가한다.
			
			spanObj.text(""); //  span태그의 텍스트를 지운다.
			spanObj.append(redMsg); // span태그에  검증에 통과하지 못했을 때 나타나는 텍스트를 넣는다.
			
			
				 
		confirmCheck = false; // 검증 통과 여부 flag에 false를 대입한다.
		}else{ // 검증에 통과했을 때,
			spanObj.show();
			spanObj.removeClass('redText');
			spanObj.addClass('greenText');
			
			spanObj.text("");
			spanObj.append(greenMsg);
	
		
			confirmCheck = true;
		}
		
	});
	
	inputObj2.focusout(function(){ // 포커스가 input태그에서 벗어났을 때 실행,
		var inputValue = inputObj2.val();
		if(confirmCheck || inputValue == ""){ // 검증에 통과를 했거나 input태그에 입력 값이 없을 경우,
			spanObj.hide(); // span태그를 숨긴다.
		}
	});
} 
function spanValidation(spanObj, inputObj, validFuncArray, redMsg, greenMsg, marginLeftPx){
	spanObj.css("margin-left", marginLeftPx); // span태그의 좌측 폭을 설정해준다.
	
	var confirmCheck = false; // 검증에 통과 여부에 사용할 flag
	
	spanObj.hide(); // span태그를 숨긴다.
	
	inputObj.bind('focusin keyup', function(){ // input태그에 포커스가 들어오거나 키가 눌렸을 때 실행됨
		var inputValue = inputObj.val();
		
		var funcResult = true; // 함수 실행 결과를 담을 flag
		
		for(i=0; i<validFuncArray.length; i++){ // 검증에 사용할 함수명 배열을 반복문으로 돌린다.
			var funcName = validFuncArray[i]; // 배열에서 함수명을 하나씩 뽑아낸다. 
			var funcObj = window[funcName]; // 함수명(string)을 객체(object)로 받는다.
			funcResult = funcObj(inputValue); // 해당 함수를 실행하여 결과값(true/false)을 변수에 담는다. 만약 함수 하나라도 통과를 하지 못하면 false가 된다.
			if(!funcResult){ // 검증에 통과하지 못한 함수가 있을 경우 반복문 탈출
				break;
			}
		}
		
		if(!funcResult){ // 검증에 통과하지 못했을 때,
			spanObj.show(); // span태그 보여준다.
			spanObj.removeClass('greenText'); // span태그에 greenText 클래스를 삭제한다.
			spanObj.addClass('redText'); // span태그에 redText 클래스를 추가한다.
			
			spanObj.text(""); //  span태그의 텍스트를 지운다.
			spanObj.append(redMsg); // span태그에  검증에 통과하지 못했을 때 나타나는 텍스트를 넣는다.
			
			confirmCheck = false; // 검증 통과 여부 flag에 false를 대입한다.
		}else{ // 검증에 통과했을 때,
			spanObj.show();
			spanObj.removeClass('redText');
			spanObj.addClass('greenText');
			
			spanObj.text("");
			spanObj.append(greenMsg);
			
			confirmCheck = true;
		}
		
	});
	
	inputObj.focusout(function(){ // 포커스가 input태그에서 벗어났을 때 실행,
		var inputValue = inputObj.val();
		if(confirmCheck || inputValue == ""){ // 검증에 통과를 했거나 input태그에 입력 값이 없을 경우,
			spanObj.hide(); // span태그를 숨긴다.
		}
	});
}
//비밀번호 일치여부 검사
function pwdSame(str, str2){
	if(str==str2){ return true;}
	 return false;
}
// 영문만 입력받도록 검증
function isAlphabetForSpan(str){
	var check = /[^A-Za-z\s]/;
	if(check.test(str)){
		return false;
	}
	return true;
}

function phoneCheck(str){
	var check = /^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-?[0-9]{3,4}-?[0-9]{4}$/;
	if(!check.test(str)){
		return false;
	}
	return true;
}
//영문 숫자혼합 6~20이내
function chkPwd(str){
 var reg_pwd = /^.*(?=.{6,20})(?=.*[0-9])(?=.*[a-zA-Z]).*$/;
 if(!reg_pwd.test(str)){
  return false;
 }
 return true;
}
// 공백 허용하지 않도록 검증
function spaceCheck(inputVal){
	var invalid = " ";
	
	if(inputVal.indexOf(invalid) > -1){
		return false;
	}else if(inputVal.length < 1){
		return false;
	}else{
		return true;
	}
}