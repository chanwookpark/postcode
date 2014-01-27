<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html class="no-js">
<head>
<<<<<<< HEAD
    <meta charset="UTF-8">
=======
<meta charset="UTF-8">
>>>>>>> 317c8320f330ecb712c28422b27778753554c81f
    <title>우편번호 찾기</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="robots" content="noindex, nofollow">
    <meta name="viewport" content="width=device-width">
    <link rel="stylesheet" href="http://soopul.com/git/style/postcode.css">
</head>
<body class="postcode_ index_">
<section class="searchbox">
    <h1>우편번호 목록</h1>
    <form
            id="form1"
            class="form1"
            action="/postcode/search"
            method="GET">

        <fieldset>
<<<<<<< HEAD
            <legend>템플릿설정</legend>
            <p>
                <label>
                    resultItemType:
                    <select name="_var" id="resultItemType">
                        <option value="resultItemType:row1" selected="selected">row1</option>
                    </select>
                </label>

                <label>
                    pagingType:
                    <select name="_var" id="pagingType">
                        <option value="pagingType:paging1" selected="selected">1</option>
                        <option value="pagingType:paging1A">1A</option>
                        <option value="pagingType:paging1B">1B</option>
                        <option value="pagingType:paging2">2</option>
                        <option value="pagingType:paging3">3</option>
                    </select>
                </label>

                <label>
                    navigationSize:
                    <input type="text" name="_navigationSize" id="_navigationSize" class="int" value="5">
                </label>

                <label>
                    pageItemSize:
                    <input type="text" name="_pageItemSize" id="_pageItemSize" class="int" value="10">
                </label>
            </p>
        </fieldset>

        <fieldset>
=======
>>>>>>> 317c8320f330ecb712c28422b27778753554c81f
            <legend>검색 카테고리</legend>
            <p>
                <label>
                    <input type="radio" name="addressType" id="addressTypeA" value="PLAIN" checked="checked">
                    주소
                </label>
                <label>
                    <input type="radio" name="addressType" id="addressTypeB" value="STREET">
                    도로명
                </label>
            </p>
        </fieldset>

        <p class="inputTextBox">
            <label>
                <span class="lh">검색어입력</span>
                <input type="text" name="address" id="address" placeholder="도로명입력">
            </label>
            <input type="submit" value="조회">
        </p>
    </form>

    <section class="resultsbox">
        <h1>검색결과</h1>

        <div id="results">
            ${_view}
        </div>
    </section>
</section>

<script src="http://soopul.com/git/lib/modernizr.custom.11001.js"></script>
<script src="http://soopul.com/git/lib/json2.js"></script>
<script src="http://soopul.com/git/script/xhr.js"></script>
<script src="http://soopul.com/git/script/postcode.js"></script>

</body>
</html>