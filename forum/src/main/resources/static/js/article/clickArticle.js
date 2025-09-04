/**
 * トップページで記事を押下した場合の処理
 */

 let articles = document.getElementsByClassName('article');
 
 for(let i = 0; i < articles.length; i++){
	 articles.item(i).addEventListener('click', ()=>{
		 let article_id = articles.item(i).getElementsByClassName('article_id').item(0).textContent;
		 console.log('記事ID：' + article_id);
		 window.location.href='http://www.localhost:8080/article?id=' + article_id;
	 },false);
 }