(function(){dust.register("postcode-search",body_0);function body_0(chk,ctx){return chk.write("<section id=\"results\" class=\"results type_").reference(ctx.get("addressType"),ctx,"h").write("\"><h1>검색결과</h1><table id=\"postcodeResultTable\"><caption><p>테이블 1. 도로명주소, 도로명, 키워드로 검색한 정보중 순번, 우편번호, 주소만을 걸러내어 보여준다</p></caption><colgroup><col><col><col></colgroup><thead><tr><th class=\"postCode\">우편번호</th><th class=\"address\">주소</th></tr></thead><tbody><!-- ").section(ctx.get("contents"),ctx,{"block":body_1},null).write(" --></tr></tbody></table><nav class=\"paging-nav\" id=\"paging-nav\"><h1>우편번호 검색 결과 페이지 이동</h1>").section(ctx.get("page"),ctx,{"block":body_2},null).write("</nav></section>");}function body_1(chk,ctx){return chk.write(" --><tr><td>").reference(ctx.get("postCode"),ctx,"h").write("</td><td><a>").reference(ctx.get("address"),ctx,"h").write("</a></td><!-- ");}function body_2(chk,ctx){return chk.write("<div>").exists(ctx.get("enablePrevious"),ctx,{"block":body_3},null).section(ctx.get("navigationNumber"),ctx,{"block":body_6},{"len":ctx.getPath(false,["navigationNumber","length"])}).exists(ctx.get("enableNext"),ctx,{"block":body_11},null).write("</div>");}function body_3(chk,ctx){return chk.helper("math",ctx,{"block":body_4},{"key":body_5,"method":"subtract","operand":"1"});}function body_4(chk,ctx){return chk.write("<a title=\"").reference(ctx.get("selectKey"),ctx,"h").write("\">이전</a> ");}function body_5(chk,ctx){return chk.reference(ctx.getPath(false,["navigationNumber","0"]),ctx,"h");}function body_6(chk,ctx){return chk.helper("eq",ctx,{"else":body_7,"block":body_8},{"key":body_9,"value":body_10});}function body_7(chk,ctx){return chk.write("<a title=\"").reference(ctx.getPath(true,[]),ctx,"h").write("\">").reference(ctx.getPath(true,[]),ctx,"h").write("</a> ");}function body_8(chk,ctx){return chk.write("<a class=\"on\" title=\"").reference(ctx.getPath(true,[]),ctx,"h").write("\">").reference(ctx.getPath(true,[]),ctx,"h").write("</a> ");}function body_9(chk,ctx){return chk.reference(ctx.getPath(true,[]),ctx,"h");}function body_10(chk,ctx){return chk.reference(ctx.get("pageNumber"),ctx,"h");}function body_11(chk,ctx){return chk.helper("math",ctx,{"block":body_12},{"key":body_15,"method":"multiply","operand":body_16});}function body_12(chk,ctx){return chk.helper("math",ctx,{"block":body_13},{"key":body_14,"method":"add","operand":"1"});}function body_13(chk,ctx){return chk.write("<a title=\"").reference(ctx.get("selectKey"),ctx,"h").write("\">다음</a>");}function body_14(chk,ctx){return chk.reference(ctx.get("selectKey"),ctx,"h");}function body_15(chk,ctx){return chk.reference(ctx.getPath(false,["navigationNumber","length"]),ctx,"h");}function body_16(chk,ctx){return chk.reference(ctx.get("numberGroupCount"),ctx,"h");}return body_0;})();