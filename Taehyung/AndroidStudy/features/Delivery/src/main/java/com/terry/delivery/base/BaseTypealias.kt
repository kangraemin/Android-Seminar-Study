package com.terry.delivery.base

import android.view.LayoutInflater
import android.view.ViewGroup

/*
 * Created by Taehyung Kim on 2021-08-29
 */
typealias ActivityInflater<B> = (LayoutInflater) -> B

typealias FragmentInflater<B> = (LayoutInflater, ViewGroup?, Boolean) -> B