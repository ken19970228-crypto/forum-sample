/**
 * 記事入力画面の文字数カウント
 */
import {charCounter} from '../inputCounter.js';
const TITLE_MAX = 300;
const TEXT_MAX = 3000;

let titleElem = document.getElementById('title_input');
charCounter('title_input','title_length', TITLE_MAX);
titleElem.addEventListener('input', ()=>{
	charCounter('title_input','title_length', TITLE_MAX);
}, false);

let textElem = document.getElementById('text_input');
charCounter('text_input', 'text_length', TEXT_MAX);
textElem.addEventListener('input', ()=>{
	charCounter('text_input', 'text_length', TEXT_MAX);
},false);