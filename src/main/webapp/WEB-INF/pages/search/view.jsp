<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<meta charset="UTF-8">
<html>
<title>우편번호 찾기</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="robots" content="noindex, nofollow">
<meta name="viewport" content="width=device-width">
<link rel="stylesheet" href="http://soopul.com/mullae/postcode/postcode.css">
<h1>dev-1</h1>
<section class="postcodeSearch">
        <h1>우편번호 목록</h1>
        <form id="postcodeSearchForm" name="postcodeSearchForm" class="form" action="http://soopul.com" method="GET">
                <fieldset>
                        <legend>검색 카테고리 선택</legend>
                        <label for="r1A">
                                <input type="radio" name="r1" id="r1A" value="A" checked="checked">
                                <span class="rowh">주소</span>
                        </label>
                        <br>
                        <label for="r1R">
                                <input type="radio" name="r1" id="r1R" value="R">
                                <span class="rowh">도로명</span>
                        </label>
                </fieldset>
                <p class="inputTextBox">
                        <label for="t1">
                                <span class="rowh">검색어 입력</span>
                                <input type="text" name="t1" id="t1" placeholder="도로명 입력">
                                <input type="submit" id="submit" value="조회">
                        </label>
                </p>
                <p class="controls">
                        <!--
                        <a href="#" target="_blank">등록</a>
                        <a href="#" target="_blank">엑셀등록</a>
                        -->
                </p>
        </form>



        <div id="resutls" class="results">
                ${view}
        </div><!-- #results -->
</section>

<script src="http://soopul.com/mullae/postcode/res/postcode.js"></script></html>
<script src="http://image.gsshop.com/deal/static/html/mc/script/lib/json2.js"></script>
<script src="http://image.gsshop.com/deal/static/html/mc/script/lib/xhr.js"></script>