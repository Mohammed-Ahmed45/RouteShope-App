package com.mohamed.data.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.google.gson.Gson
import com.mohamed.data.interceptor.UserDataStore
import com.mohamed.data.repositories.auth.SignInRepoImp
import com.mohamed.data.repositories.auth.SignUpRepoImp
import com.mohamed.data.repositories.brands.BrandsRepoImp
import com.mohamed.data.repositories.cart.CartRepoImp
import com.mohamed.data.repositories.categories.CategoriesRepoImp
import com.mohamed.data.repositories.datasource.AuthOfflineDataSource
import com.mohamed.data.repositories.datasource.AuthOfflineDataSourceImpl
import com.mohamed.data.repositories.product.ProductDetailsRepoImp
import com.mohamed.data.repositories.product.ProductRepoImp
import com.mohamed.data.repositories.subcategory.SubCategoryRepoImp
import com.mohamed.data.repositories.wishlist.WishListRepoImp
import com.mohamed.domain.repositories.auth.SignInRepo
import com.mohamed.domain.repositories.auth.SignUpRepo
import com.mohamed.domain.repositories.brands.BrandsRepo
import com.mohamed.domain.repositories.cart.CartRepo
import com.mohamed.domain.repositories.categories.CategoriesRepo
import com.mohamed.domain.repositories.categories.SubCategoryRepo
import com.mohamed.domain.repositories.product.ProductDetailsRepo
import com.mohamed.domain.repositories.product.ProductRepo
import com.mohamed.domain.repositories.wishlist.WishListRepo
import dagger.Binds
import dagger.Module
import dagger.Provides
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
    abstract fun bindProductDetails(productDetailsRepoImp: ProductDetailsRepoImp): ProductDetailsRepo


    @Binds
    @Singleton
    abstract fun bindSignUp(signUpRepoImp: SignUpRepoImp): SignUpRepo

    @Binds
    @Singleton
    abstract fun bindSignIn(signInRepoImp: SignInRepoImp): SignInRepo


    @Binds
    @Singleton
    abstract fun bindWishList(wishListRepoImp: WishListRepoImp): WishListRepo

    @Binds
    @Singleton
    abstract fun bindCartList(cartRepoImp: CartRepoImp): CartRepo

}

@Module
@InstallIn(SingletonComponent::class)
object OfflineDataSourceModule {
    @Provides
    @Singleton
    fun provideAuthOfflineDataSource(
        @UserDataStore userDataStore: DataStore<Preferences>,
        gson: Gson,

        ): AuthOfflineDataSource {
        return AuthOfflineDataSourceImpl(
            userDataStore,
            gson
        )
    }
}