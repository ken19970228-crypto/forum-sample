
/**
 * 入力欄の文字数カウント
 */
 export function charCounter(elemId, countViewElemId, max){
	 let elem = document.getElementById(elemId);
	 let count = elem.value.length;
	 let countViewElem = document.getElementById(countViewElemId);
	 countViewElem.innerText = count;
	 if(Number(countViewElem.innerText) > max){
		 countViewElem.style.color = 'red';
	 }else{
		 countViewElem.style.color = 'black';
	 }
 }