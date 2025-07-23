package com.mohamed.data.di

import com.mohamed.data.repositories.auth.SignInRepoImp
import com.mohamed.data.repositories.auth.SignUpRepoImp
import com.mohamed.data.repositories.brands.BrandsRepoImp
import com.mohamed.data.repositories.categories.CategoriesRepoImp
import com.mohamed.data.repositories.product.ProductRepoImp
import com.mohamed.data.repositories.subcategory.SubCategoryRepoImp
import com.mohamed.domain.repositories.auth.SignInRepo
import com.mohamed.domain.repositories.auth.SignUpRepo
import com.mohamed.domain.repositories.brands.BrandsRepo
import com.mohamed.domain.repositories.categories.CategoriesRepo
import com.mohamed.domain.repositories.categories.SubCategoryRepo
import com.mohamed.domain.repositories.product.ProductRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindCategoriesRepo(categoriesRepoImp: CategoriesRepoImp): CategoriesRepo

    @Binds
    @Singleton
    abstract fun bindBrandsRepo(brandsRepoImp: BrandsRepoImp): BrandsRepo

    @Binds
    @Singleton
    abstract fun bindSubCategory(subCategoryRepoImp: SubCategoryRepoImp): SubCategoryRepo

    @Binds
    @Singleton
    abstract fun bindProduct(productRepoImp: ProductRepoImp): ProductRepo


    @Binds
    @Singleton
    abstract fun bindSignUp(signUpRepoImp: SignUpRepoImp): SignUpRepo

    @Binds
    @Singleton
    abstract fun bindSignIn(signInRepoImp: SignInRepoImp): SignInRepo

}