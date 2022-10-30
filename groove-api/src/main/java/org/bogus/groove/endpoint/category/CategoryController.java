package org.bogus.groove.endpoint.category;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.CommonResponse;
import org.bogus.groove.common.enumeration.CategoryGroup;
import org.bogus.groove.domain.category.CategoryService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "category-controller")
@RequestMapping("/api/category")
@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @Operation(summary = "카테고리 생성")
    @PostMapping
    public CommonResponse<Void> createCategory(@RequestBody CategoryCreateRequest request) {
        categoryService.createCategory(request.getName(), request.getCategoryGroup());
        return CommonResponse.success();
    }

    @Operation(summary = "카테고리 그룹별 카테고리 리스트 조회 (COMMUNITY, TRAINING)")
    @GetMapping("/category/{categoryGroup}")
    public CommonResponse<List<CategoryResponse>> getCategoryList(@PathVariable CategoryGroup categoryGroup) {
        return CommonResponse.success(
            categoryService.getCategoryList(categoryGroup).stream().map(CategoryResponse::new).collect(Collectors.toList()));
    }

    @Operation(summary = "카테고리 수정")
    @PutMapping("/{categoryId}")
    public CommonResponse<Void> updateCategory(@PathVariable Long categoryId, @RequestBody CategoryUpdateRequest request) {
        categoryService.updateCategory(categoryId, request.getName(), request.getCategoryGroup());
        return CommonResponse.success();
    }

    @Operation(summary = "카테고리 삭제")
    @DeleteMapping("/{categoryId}")
    public CommonResponse<Void> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return CommonResponse.success();
    }
}
